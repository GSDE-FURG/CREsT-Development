/*********************************************************************
*
*      Copyright (C) 2005 Andrew Khan
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

using CSharpJExcel.Jxl;
using CSharpJExcel.Jxl.Common;


namespace CSharpJExcel.Jxl.Biff
	{
	/**
	 * Helper to get the Microsoft encoded URL from the given string
	 */
	public class EncodedURLHelper
		{
		/**
		 * The logger
		 */
		//  private static Logger logger = Logger.getLogger(EncodedURLHelper.class);

		// The control codes
		private const byte msDosDriveLetter = 0x01;
		private const byte sameDrive = 0x02;
		private const byte endOfSubdirectory = 0x03;
		private const byte parentDirectory = 0x04;
		private const byte unencodedUrl = 0x05;

		public static byte[] getEncodedURL(string s,WorkbookSettings ws)
			{
			if (s.StartsWith("http:"))
				return getURL(s,ws);
			else if (s.StartsWith("https:"))		// CML
				return getURL(s, ws);
			else
				return getFile(s,ws);
			}

		private static byte[] getFile(string s,WorkbookSettings ws)
			{
			ByteArray byteArray = new ByteArray();

			int pos = 0;
			if (s[1] == ':')
				{
				// we have a drive letter
				byteArray.add(msDosDriveLetter);
				byteArray.add((byte)s[0]);
				pos = 2;
				}
			else if (s[pos] == '\\' || s[pos] == '/')
				{
				byteArray.add(sameDrive);
				}

			while (s[pos] == '\\' ||
				   s[pos] == '/')
				{
				pos++;
				}

			while (pos < s.Length)
				{
				int nextSepIndex1 = s.IndexOf('/',pos);
				int nextSepIndex2 = s.IndexOf('\\',pos);
				int nextSepIndex = 0;
				string nextFileNameComponent = null;

				if (nextSepIndex1 != -1 && nextSepIndex2 != -1)
					{
					// choose the smallest (ie. nearest) separator
					nextSepIndex = System.Math.Min(nextSepIndex1,nextSepIndex2);
					}
				else if (nextSepIndex1 == -1 || nextSepIndex2 == -1)
					{
					// chose the maximum separator
					nextSepIndex = System.Math.Max(nextSepIndex1,nextSepIndex2);
					}

				if (nextSepIndex == -1)
					{
					// no more separators
					nextFileNameComponent = s.Substring(pos);
					pos = s.Length;
					}
				else
					{
					nextFileNameComponent = s.Substring(pos,nextSepIndex);
					pos = nextSepIndex + 1;
					}

				if (nextFileNameComponent.Equals("."))
					{
					// current directory - do nothing
					}
				else if (nextFileNameComponent.Equals(".."))
					{
					// parent directory
					byteArray.add(parentDirectory);
					}
				else
					{
					// add the filename component
					byteArray.add(StringHelper.getBytes(nextFileNameComponent,
														ws));
					}

				if (pos < s.Length)
					{
					byteArray.add(endOfSubdirectory);
					}
				}

			return byteArray.getBytes();
			}

		private static byte[] getURL(string s,WorkbookSettings ws)
			{
			ByteArray byteArray = new ByteArray();
			byteArray.add(unencodedUrl);
			byteArray.add((byte)s.Length);
			byteArray.add(StringHelper.getBytes(s,ws));
			return byteArray.getBytes();
			}
		}
	}
