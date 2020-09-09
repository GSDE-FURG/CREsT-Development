/*********************************************************************
*
*      Copyright (C) 2002 Andrew Khan
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
*
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
***************************************************************************/

// Port to C# 
// Chris Laforet
// Wachovia, a Wells-Fargo Company
// Feb 2010

using System.Collections.Generic;


namespace CSharpJExcel.Jxl.Biff.Formula
	{
	/**
	 * An enumeration detailing the Excel parsed tokens
	 * A particular token may be associated with more than one token code
	 */
	public class Token
		{
		/**
		 * The array of values which apply to this token
		 */
		public int[] value;

		/**
		 * All available tokens, keyed on value
		 */
		private static Dictionary<int,Token> tokens = new Dictionary<int,Token>(20);

		/**
		 * Constructor
		 * Sets the token value and adds this token to the array of all token
		 *
		 * @param v the biff code for the token
		 */
		private Token(int v)
			{
			value = new int[] { v };

			tokens.Add(v,this);
			}

		/**
		 * Constructor
		 * Sets the token value and adds this token to the array of all token
		 *
		 * @param v the biff code for the token
		 */
		private Token(int v1,int v2)
			{
			value = new int[] { v1,v2 };

			tokens.Add(v1,this);
			tokens.Add(v2,this);
			}

		/**
		 * Constructor
		 * Sets the token value and adds this token to the array of all token
		 *
		 * @param v the biff code for the token
		 */
		private Token(int v1,int v2,int v3)
			{
			value = new int[] { v1,v2,v3 };

			tokens.Add(v1, this);
			tokens.Add(v2, this);
			tokens.Add(v3, this);
			}

		/**
		 * Constructor
		 * Sets the token value and adds this token to the array of all token
		 *
		 * @param v the biff code for the token
		 */
		private Token(int v1,int v2,int v3,int v4)
			{
			value = new int[] { v1,v2,v3,v4 };

			tokens.Add(v1, this);
			tokens.Add(v2, this);
			tokens.Add(v3, this);
			tokens.Add(v4, this);
			}

		/**
		 * Constructor
		 * Sets the token value and adds this token to the array of all token
		 *
		 * @param v the biff code for the token
		 */
		private Token(int v1,int v2,int v3,int v4,int v5)
			{
			value = new int[] { v1,v2,v3,v4,v5 };

			tokens.Add(v1, this);
			tokens.Add(v2, this);
			tokens.Add(v3, this);
			tokens.Add(v4, this);
			tokens.Add(v5, this);
			}

		/**
		 * Gets the token code for the specified token
		 * 
		 * @return the token code.  This is the first item in the array
		 */
		public byte getCode()
			{
			return (byte)value[0];
			}

		/**
		 * Gets the reference token code for the specified token.  This is always
		 * the first on the list
		 * 
		 * @return the token code.  This is the first item in the array
		 */
		public byte getReferenceCode()
			{
			return (byte)value[0];
			}

		/**
		 * Gets the an alternative token code for the specified token
		 * Used for certain types of volatile function
		 * 
		 * @return the token code
		 */
		public byte getCode2()
			{
			return (byte)(value.Length > 0 ? value[1] : value[0]);
			}

		/**
		 * Gets the value token code for the specified token.  This is always
		 * the second item on the list
		 * 
		 * @return the token code
		 */
		public byte getValueCode()
			{
			return (byte)(value.Length > 0 ? value[1] : value[0]);
			}

		/**
		 * Gets the type object from its integer value
		 */
		public static Token getToken(int v)
			{
			if (!tokens.ContainsKey(v))
				return UNKNOWN;
			return tokens[v];
			}

		// Operands
		public static readonly Token REF = new Token(0x44,0x24,0x64);
		public static readonly Token REF3D = new Token(0x5a,0x3a,0x7a);
		public static readonly Token MISSING_ARG = new Token(0x16);
		public static readonly Token STRING = new Token(0x17);
		public static readonly Token ERR = new Token(0x1c);
		public static readonly Token BOOL = new Token(0x1d);
		public static readonly Token INTEGER = new Token(0x1e);
		public static readonly Token DOUBLE = new Token(0x1f);
		public static readonly Token REFERR = new Token(0x2a,0x4a,0x6a);
		public static readonly Token REFV = new Token(0x2c,0x4c,0x6c);
		public static readonly Token AREAV = new Token(0x2d,0x4d,0x6d);
		public static readonly Token MEM_AREA = new Token(0x26,0x46,0x66);
		public static readonly Token AREA = new Token(0x25,0x65,0x45);
		public static readonly Token NAMED_RANGE = new Token(0x23,0x43,0x63);
		//need 0x23 for data validation references
		public static readonly Token NAME = new Token(0x39,0x59);
		public static readonly Token AREA3D = new Token(0x3b,0x5b);

		// Unary Operators
		public static readonly Token UNARY_PLUS = new Token(0x12);
		public static readonly Token UNARY_MINUS = new Token(0x13);
		public static readonly Token PERCENT = new Token(0x14);
		public static readonly Token PARENTHESIS = new Token(0x15);

		// Binary Operators
		public static readonly Token ADD = new Token(0x3);
		public static readonly Token SUBTRACT = new Token(0x4);
		public static readonly Token MULTIPLY = new Token(0x5);
		public static readonly Token DIVIDE = new Token(0x6);
		public static readonly Token POWER = new Token(0x7);
		public static readonly Token CONCAT = new Token(0x8);
		public static readonly Token LESS_THAN = new Token(0x9);
		public static readonly Token LESS_EQUAL = new Token(0xa);
		public static readonly Token EQUAL = new Token(0xb);
		public static readonly Token GREATER_EQUAL = new Token(0xc);
		public static readonly Token GREATER_THAN = new Token(0xd);
		public static readonly Token NOT_EQUAL = new Token(0xe);
		public static readonly Token UNION = new Token(0x10);
		public static readonly Token RANGE = new Token(0x11);

		// Functions
		public static readonly Token FUNCTION = new Token(0x41,0x21,0x61);
		public static readonly Token FUNCTIONVARARG = new Token(0x42,0x22,0x62);

		// Control
		public static readonly Token ATTRIBUTE = new Token(0x19);
		public static readonly Token MEM_FUNC = new Token(0x29,0x49,0x69);

		// Unknown token
		public static readonly Token UNKNOWN = new Token(0xffff);
		}
	}

