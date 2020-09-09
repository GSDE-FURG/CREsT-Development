/*********************************************************************
*
*      Copyright (C) 2003 Andrew Khan
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

using System.Collections;


namespace CSharpJExcel.Jxl.Biff.Drawing
	{
	/**
	 * An escher container.  This record may contain other escher containers or
	 * atoms
	 */
	public class EscherContainer : EscherRecord
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(EscherContainer.class);

		/**
		 * Initialized flag
		 */
		private bool initialized;


		/**
		 * The children of this container
		 */
		private ArrayList children;

		/**
		 * Constructor
		 *
		 * @param erd the raw data
		 */
		public EscherContainer(EscherRecordData erd)
			: base(erd)
			{
			initialized = false;
			children = new ArrayList();
			}

		/**
		 * Constructor used when writing out escher data
		 *
		 * @param type the type
		 */
		protected EscherContainer(EscherRecordType type)
			: base(type)
			{
			setContainer(true);
			children = new ArrayList();
			}

		/**
		 * Accessor for the children of this container
		 *
		 * @return the children
		 */
		public EscherRecord[] getChildren()
			{
			if (!initialized)
				initialize();

			EscherRecord[] ca = new EscherRecord[children.Count];
			int pos = 0;
			foreach (EscherRecord record in children)
				ca[pos++] = record;
			return ca;
			}

		/**
		 * Adds a child to this container
		 *
		 * @param child the item to add
		 */
		public void add(EscherRecord child)
			{
			children.Add(child);
			}

		/**
		 * Removes a child from this container
		 *
		 * @param child the item to remove
		 */
		public void remove(EscherRecord child)
			{
			children.Remove(child);
			}

		/**
		 * Initialization
		 */
		private void initialize()
			{
			int curpos = getPos() + HEADER_LENGTH;
			int endpos = System.Math.Min(getPos() + getLength(),getStreamLength());

			EscherRecord newRecord = null;

			while (curpos < endpos)
				{
				EscherRecordData erd = new EscherRecordData(getEscherStream(),curpos);

				EscherRecordType type = erd.getType();
				if (type == EscherRecordType.DGG)
					newRecord = new Dgg(erd);
				else if (type == EscherRecordType.DG)
					newRecord = new Dg(erd);
				else if (type == EscherRecordType.BSTORE_CONTAINER)
					newRecord = new BStoreContainer(erd);
				else if (type == EscherRecordType.SPGR_CONTAINER)
					newRecord = new SpgrContainer(erd);
				else if (type == EscherRecordType.SP_CONTAINER)
					newRecord = new SpContainer(erd);
				else if (type == EscherRecordType.SPGR)
					newRecord = new Spgr(erd);
				else if (type == EscherRecordType.SP)
					newRecord = new Sp(erd);
				else if (type == EscherRecordType.CLIENT_ANCHOR)
					newRecord = new ClientAnchor(erd);
				else if (type == EscherRecordType.CLIENT_DATA)
					newRecord = new ClientData(erd);
				else if (type == EscherRecordType.BSE)
					newRecord = new BlipStoreEntry(erd);
				else if (type == EscherRecordType.OPT)
					newRecord = new Opt(erd);
				else if (type == EscherRecordType.SPLIT_MENU_COLORS)
					newRecord = new SplitMenuColors(erd);
				else if (type == EscherRecordType.CLIENT_TEXT_BOX)
					newRecord = new ClientTextBox(erd);
				else
					newRecord = new EscherAtom(erd);

				children.Add(newRecord);
				curpos += newRecord.getLength();
				}

			initialized = true;
			}

		/**
		 * Gets the data for this container (and all of its children recursively
		 *
		 * @return the binary data
		 */
		public override byte[] getData()
			{
			if (!initialized)
				initialize();

			byte[] data = new byte[0];
			foreach (EscherRecord er in children)
				{
				byte[] childData = er.getData();

				if (childData != null)
					{
					byte[] newData = new byte[data.Length + childData.Length];
					System.Array.Copy(data,newData,data.Length);
					System.Array.Copy(childData,0,newData,data.Length,childData.Length);
					data = newData;
					}
				}

			return setHeaderData(data);
			}
		}
	}
