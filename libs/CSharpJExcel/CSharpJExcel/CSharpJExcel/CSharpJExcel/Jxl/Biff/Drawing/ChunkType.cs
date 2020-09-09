/*********************************************************************
*
*      Copyright (C) 2006 Andrew Khan
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


namespace CSharpJExcel.Jxl.Biff.Drawing
	{
	/**
	 * Enumeration for the various chunk types
	 */
	class ChunkType
		{
		private byte[] id;
		private string name;

		private static ChunkType[] chunkTypes = new ChunkType[0];

		private ChunkType(int d1,int d2,int d3,int d4,string n)
			{
			id = new byte[] { (byte)d1,(byte)d2,(byte)d3,(byte)d4 };
			name = n;

			ChunkType[] ct = new ChunkType[chunkTypes.Length + 1];
			System.Array.Copy(chunkTypes,0,ct,0,chunkTypes.Length);
			ct[chunkTypes.Length] = this;
			chunkTypes = ct;
			}

		public string getName()
			{
			return name;
			}

		public static ChunkType getChunkType(byte d1,byte d2,byte d3,byte d4)
			{
			byte[] cmp = new byte[] { d1,d2,d3,d4 };

			bool found = false;
			ChunkType chunk = ChunkType.UNKNOWN;

			for (int i = 0; i < chunkTypes.Length && !found; i++)
				{
				if (System.Array.Equals(chunkTypes[i].id,cmp))
					{
					chunk = chunkTypes[i];
					found = true;
					}
				}

			return chunk;
			}


		public static readonly ChunkType IHDR = new ChunkType(0x49,0x48,0x44,0x52,"IHDR");
		public static readonly ChunkType IEND = new ChunkType(0x49,0x45,0x4e,0x44,"IEND");
		public static readonly ChunkType PHYS = new ChunkType(0x70,0x48,0x59,0x73,"pHYs");
		public static readonly ChunkType UNKNOWN = new ChunkType(0xff,0xff,0xff,0xff,"UNKNOWN");
		}
	}
