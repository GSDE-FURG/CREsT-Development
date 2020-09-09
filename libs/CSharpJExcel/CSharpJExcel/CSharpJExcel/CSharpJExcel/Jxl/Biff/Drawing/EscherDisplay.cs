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

using System.IO;


namespace CSharpJExcel.Jxl.Biff.Drawing
	{
	/**
	 * Class used to display a complete hierarchically organized Escher stream
	 * The whole thing is dumped to System.out
	 *
	 * This class is only used as a debugging tool
	 */
	public class EscherDisplay
		{
		/**
		 * The escher stream
		 */
		private EscherStream stream;

		/**
		 * The writer
		 */
		private TextWriter writer;

		/**
		 * Constructor
		 *
		 * @param s the stream
		 * @param bw the writer
		 */
		public EscherDisplay(EscherStream s,TextWriter os)
			{
			stream = s;
			writer = os;
			}

		/**
		 * Display the formatted escher stream
		 *
		 * @exception IOException
		 */
		public void display()
			{
			EscherRecordData er = new EscherRecordData(stream,0);
			EscherContainer ec = new EscherContainer(er);
			displayContainer(ec,0);
			}

		/**
		 * Displays the escher container as text
		 *
		 * @param ec the escher container
		 * @param level the indent level
		 * @exception IOException
		 */
		private void displayContainer(EscherContainer ec,int level)
			{
			displayRecord(ec,level);

			// Display the contents of the container
			level++;

			EscherRecord[] children = ec.getChildren();

			for (int i = 0; i < children.Length; i++)
				{
				EscherRecord er = children[i];
				if (er.getEscherData().isContainer())
					{
					displayContainer((EscherContainer)er,level);
					}
				else
					{
					displayRecord(er,level);
					}
				}
			}

		/**
		 * Displays an escher record
		 *
		 * @param er the record to display
		 * @param level the amount of indentation
		 * @exception IOException
		 */
		private void displayRecord(EscherRecord er,int level)
			{
			indent(level);

			EscherRecordType type = er.getType();

			// Display the code
			writer.Write(System.String.Format("{0:X}",type.getValue()));
			writer.Write(" - ");

			// Display the name
			if (type == EscherRecordType.DGG_CONTAINER)
				writer.WriteLine("Dgg Container");
			else if (type == EscherRecordType.BSTORE_CONTAINER)
				writer.WriteLine("BStore Container");
			else if (type == EscherRecordType.DG_CONTAINER)
				writer.WriteLine("Dg Container");
			else if (type == EscherRecordType.SPGR_CONTAINER)
				writer.WriteLine("Spgr Container");
			else if (type == EscherRecordType.SP_CONTAINER)
				writer.WriteLine("Sp Container");
			else if (type == EscherRecordType.DGG)
				writer.WriteLine("Dgg");
			else if (type == EscherRecordType.BSE)
				writer.WriteLine("Bse");
			else if (type == EscherRecordType.DG)
				{
				Dg dg = new Dg(er.getEscherData());
				writer.WriteLine("Dg:  drawing id " + dg.getDrawingId() + " shape count " + dg.getShapeCount());
				}
			else if (type == EscherRecordType.SPGR)
				writer.WriteLine("Spgr");
			else if (type == EscherRecordType.SP)
				{
				Sp sp = new Sp(er.getEscherData());
				writer.WriteLine("Sp:  shape id " + sp.getShapeId() + " shape type " + sp.getShapeType());
				}
			else if (type == EscherRecordType.OPT)
				{
				Opt opt = new Opt(er.getEscherData());
				Opt.Property p260 = opt.getProperty(260);
				Opt.Property p261 = opt.getProperty(261);
				writer.Write("Opt (value, StringValue): ");
				if (p260 != null)
					{
					writer.Write("260: " +
								 p260.value + ", " +
								 p260.StringValue +
								 ";");
					}
				if (p261 != null)
					{
					writer.Write("261: " +
								 p261.value + ", " +
								 p261.StringValue +
								 ";");
					}
				writer.WriteLine(string.Empty);
				}
			else if (type == EscherRecordType.CLIENT_ANCHOR)
				writer.WriteLine("Client Anchor");
			else if (type == EscherRecordType.CLIENT_DATA)
				writer.WriteLine("Client Data");
			else if (type == EscherRecordType.CLIENT_TEXT_BOX)
				writer.WriteLine("Client Text Box");
			else if (type == EscherRecordType.SPLIT_MENU_COLORS)
				writer.WriteLine("Split Menu Colors");
			else
				writer.WriteLine("???");
			}

		/**
		 * Indents to the amount specified by the level
		 *
		 * @param level the level
		 * @exception IOException
		 */
		private void indent(int level)
			{
			for (int i = 0; i < level * 2; i++)
				{
				writer.Write(' ');
				}
			}
		}
	}
