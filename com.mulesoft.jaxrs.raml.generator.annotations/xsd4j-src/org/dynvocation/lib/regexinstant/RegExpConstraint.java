// RegExpInstantiator - Constraint-based value creation from regexps
// Copyright (C) 2006, 2007 Josef Spillner <spillner@rn.inf.tu-dresden.de>
//
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation; either version 3 of the License, or
// (at your option) any later version.
//
// This file is part of the RegExpInstantiator library.
// It has been created as part of Project Dynvocation, a research project
// at the Chair of Computer Networks, Faculty for Computer Sciences,
// Dresden University of Technology.
// See http://dynvocation.selfip.net/regexinstant/ for more information.

package org.dynvocation.lib.regexinstant;

/**
 * \brief Constraint for regular expressions.
 *
 * Constraints can be applied to regexps so that some of their
 * liberties, like the number of occurrences of single characters
 * or terms (groups), are limited in their range.
 * Note that constraints apply to the instances generated by
 * regexps, not to regexps themselves.
 *
 * In the extreme case, the constraint might result in no
 * possible string matching the (constrained) regexp. Therefore,
 * constraints are applied via \ref RegExpInstantiator::constrain,
 * and are rejected if this is the case.
 *
 * Constraint objects can be configured using \ref addConstraint.
 * It is good habit to check the internal consistence with
 * \ref isValid after doing so.
 *
 * @author Josef Spillner <spillner@rn.inf.tu-dresden.de>
 */
public class RegExpConstraint extends Base
{
	/** The minimum length of the instantiated regexp. */
	public static final int MIN_LENGTH = 1;
	/** The maximum length of the instantiated regexp. */
	public static final int MAX_LENGTH = 2;

	// Validity state and constraints configuration
	private int minlength = -1;
	private int maxlength = -1;
	private boolean invalid = false;

	/**
	 * Default constructor.
	 *
	 * Produces a valid, but otherwise not useful constraint
	 * object which does not constrain in any way.
	 */
	public RegExpConstraint()
	{
		super();

		// nothing to do, variables are pre-initialised
	}

	/**
	 * Returns whether or not this constraint is valid.
	 *
	 * Depending on the configuration, internal inconsistencies
	 * might have led to invalidity.
	 * Note that this only refers to the internal state,
	 * not to possible applications of this constraint on
	 * some regular expressions.
	 *
	 * @return Validity of this constraint
	 */
	public boolean isValid()
	{
		return !invalid;
	}

	/**
	 * Returns the minimum length.
	 *
	 * This property describes the minimum length an
	 * instantiated regexp might have.
	 *
	 * @return Minimum length, or -1 if unconfigured
	 */
	public int getMinLength()
	{
		return this.minlength;
	}

	/**
	 * Returns the maximum length.
	 *
	 * This property describes the maximum length an
	 * instantiated regexp might have.
	 *
	 * @return Maximum length, or -1 if unconfigured
	 */
	public int getMaxLength()
	{
		return this.maxlength;
	}

	/**
	 * Configure a constraint property.
	 *
	 * Properties such as the minimum and maximum length
	 * might be configured here.
	 *
	 * @param constraint One of the property constants
	 * @param value The value for this property
	 */
	public void addConstraint(int constraint, int value)
	{
		if(constraint == MIN_LENGTH)
		{
			this.minlength = value;
		}
		else if(constraint == MAX_LENGTH)
		{
			this.maxlength = value;
		}
		else
		{
			System.out.println("constraint error: invalid constraint specified");
			this.invalid = true;
		}

		if((this.minlength != -1) && (this.maxlength != -1))
		{
			if(this.minlength > this.maxlength)
			{
				System.out.println("constraint error: min > max");
				this.invalid = true;
			}
		}

		if((this.minlength < -1) || (this.maxlength < -1))
		{
			System.out.println("constraint error: min/max are negative");
			this.invalid = true;
		}
	}
}

