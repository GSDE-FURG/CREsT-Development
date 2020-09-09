/*********************************************************************
*
*      Copyright (C) 2004 Andrew Khan
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
using System.Collections.Generic;
using CSharpJExcel.Jxl.Common;
using CSharpJExcel.Jxl.Write.Biff;


namespace CSharpJExcel.Jxl.Biff.Drawing
	{
	/**
	 * This class contains the Excel picture data in Escher format for the
	 * entire workbook
	 */
	public class DrawingGroup : EscherStream
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(DrawingGroup.class);

		/**
		 * The escher data read in from file
		 */
		private byte[] drawingData;

		/**
		 * The top level escher container
		 */
		private EscherContainer escherData;

		/**
		 * The Bstore container, which contains all the drawing data
		 */
		private BStoreContainer bstoreContainer;

		/**
		 * The initialized flag
		 */
		private bool initialized;

		/**
		 * The list of user added drawings
		 */
		private ArrayList drawings;

		/**
		 * The number of blips
		 */
		private int numBlips;

		/**
		 * The number of charts
		 */
		private int numCharts;

		/**
		 * The number of shape ids used on the second Dgg cluster
		 */
		private int drawingGroupId;

		/**
		 * Flag which indicates that at least one of the drawings has been omitted
		 * from the worksheet
		 */
		private bool drawingsOmitted;

		/**
		 * The origin of this drawing group
		 */
		private Origin origin;

		/**
		 * A hash map of images keyed on the file path, containing the
		 * reference count
		 */
		private Dictionary<string,Drawing> imageFiles;

		/**
		 * A count of the next available object id
		 */
		private uint maxobjectId;

		/**
		 * The maximum shape id so far encountered
		 */
		private int maxShapeId;

		/**
		 * Constructor
		 *
		 * @param o the origin of this drawing group
		 */
		public DrawingGroup(Origin o)
			{
			origin = o;
			initialized = o == Origin.WRITE ? true : false;
			drawings = new ArrayList();
			imageFiles = new Dictionary<string,Drawing>();
			drawingsOmitted = false;
			maxobjectId = 1;
			maxShapeId = 1024;
			}

		/**
		 * Copy constructor
		 * Uses a shallow copy for most things, since as soon as anything
		 * is changed, the drawing group is invalidated and all the data blocks
		 * regenerated
		 *
		 * @param dg the drawing group to copy
		 */
		public DrawingGroup(DrawingGroup dg)
			{
			drawingData = dg.drawingData;
			escherData = dg.escherData;
			bstoreContainer = dg.bstoreContainer;
			initialized = dg.initialized;
			drawingData = dg.drawingData;
			escherData = dg.escherData;
			bstoreContainer = dg.bstoreContainer;
			numBlips = dg.numBlips;
			numCharts = dg.numCharts;
			drawingGroupId = dg.drawingGroupId;
			drawingsOmitted = dg.drawingsOmitted;
			origin = dg.origin;
//			imageFiles = (HashMap)dg.imageFiles.clone();
			imageFiles = new Dictionary<string,Drawing>(dg.imageFiles);
			maxobjectId = dg.maxobjectId;
			maxShapeId = dg.maxShapeId;

			// Create this as empty, because all drawings will get added later
			// as part of the sheet copy process
			drawings = new ArrayList();
			}

		/**

		/**
		 * Adds in a drawing group record to this drawing group.  The binary
		 * data is extracted from the drawing group and added to a single
		 * byte array
		 *
		 * @param mso the drawing group record to add
		 */
		public void add(MsoDrawingGroupRecord mso)
			{
			addData(mso.getData());
			}

		/**
		 * Adds a continue record to this drawing group.  the binary data is
		 * extracted and appended to the byte array
		 *
		 * @param cont the continue record
		 */
		public void add(CSharpJExcel.Jxl.Read.Biff.Record cont)
			{
			addData(cont.getData());
			}

		/**
		 * Adds the mso record data to the drawing data
		 *
		 * @param msodata the raw mso data
		 */
		private void addData(byte[] msodata)
			{
			if (drawingData == null)
				{
				drawingData = new byte[msodata.Length];
				System.Array.Copy(msodata,0,drawingData,0,msodata.Length);
				return;
				}

			// Grow the array
			byte[] newdata = new byte[drawingData.Length + msodata.Length];
			System.Array.Copy(drawingData,0,newdata,0,drawingData.Length);
			System.Array.Copy(msodata,0,newdata,drawingData.Length,msodata.Length);
			drawingData = newdata;
			}

		/**
		 * Adds a drawing to the drawing group
		 *
		 * @param d the drawing to add
		 */
		public void addDrawing(DrawingGroupObject d)
			{
			drawings.Add(d);
			maxobjectId = System.Math.Max(maxobjectId,d.getObjectId());
			maxShapeId = System.Math.Max(maxShapeId,d.getShapeId());
			}

		/**
		 * Adds a  chart to the drawing group
		 *
		 * @param c the chart
		 */
		public void add(Chart c)
			{
			numCharts++;
			}

		/**
		 * Adds a drawing from the public, writable interface
		 *
		 * @param d the drawing to add
		 */
		public void add(DrawingGroupObject d)
			{
			if (origin == Origin.READ)
				{
				origin = Origin.READ_WRITE;
				BStoreContainer bsc = getBStoreContainer(); // force initialization
				Dgg dgg = (Dgg)escherData.getChildren()[0];
				drawingGroupId = dgg.getCluster(1).drawingGroupId - numBlips - 1;
				numBlips = bsc != null ? bsc.getNumBlips() : 0;

				if (bsc != null)
					{
					Assert.verify(numBlips == bsc.getNumBlips());
					}
				}

			if (!(d is Drawing))
				{
				// Assign a new object id and add it to the list
				//      drawings.add(d);
				maxobjectId++;
				maxShapeId++;
				d.setDrawingGroup(this);
				d.setObjectId(maxobjectId,numBlips + 1,maxShapeId);
				if (drawings.Count > maxobjectId)
					{
					//logger.warn("drawings length " + drawings.Count +
					//            " exceeds the max object id " + maxobjectId);
					}
				//      numBlips++;
				return;
				}

			Drawing drawing = (Drawing)d;

			// See if this is referenced elsewhere
			Drawing refImage = null;
			if (imageFiles.ContainsKey(d.getImageFilePath()))
				refImage = imageFiles[d.getImageFilePath()];

			if (refImage == null)
				{
				// There are no other references to this drawing, so assign
				// a new object id and put it on the hash map
				maxobjectId++;
				maxShapeId++;
				drawings.Add(drawing);
				drawing.setDrawingGroup(this);
				drawing.setObjectId(maxobjectId,numBlips + 1,maxShapeId);
				numBlips++;
				imageFiles.Add(drawing.getImageFilePath(),drawing);
				}
			else
				{
				// This drawing is used elsewhere in the workbook.  Increment the
				// reference count on the drawing, and set the object id of the drawing
				// passed in
				refImage.setReferenceCount(refImage.getReferenceCount() + 1);
				drawing.setDrawingGroup(this);
				drawing.setObjectId(refImage.getObjectId(),
									refImage.getBlipId(),
									refImage.getShapeId());
				}
			}

		/**
		 * Interface method to remove a drawing from the group
		 *
		 * @param d the drawing to remove
		 */
		public void remove(DrawingGroupObject d)
			{
			// Unless there are real images or some such, it is possible that
			// a BStoreContainer will not be present.  In that case simply return
			if (getBStoreContainer() == null)
				{
				return;
				}

			if (origin == Origin.READ)
				{
				origin = Origin.READ_WRITE;
				numBlips = getBStoreContainer().getNumBlips();
				Dgg dgg = (Dgg)escherData.getChildren()[0];
				drawingGroupId = dgg.getCluster(1).drawingGroupId - numBlips - 1;
				}

			// Get the blip
			EscherRecord[] children = getBStoreContainer().getChildren();
			BlipStoreEntry bse = (BlipStoreEntry)children[d.getBlipId() - 1];

			bse.dereference();

			if (bse.getReferenceCount() == 0)
				{
				// Remove the blip
				getBStoreContainer().remove(bse);

				// Adjust blipId on the other blips
				foreach (DrawingGroupObject drawing in drawings)
					{
					if (drawing.getBlipId() > d.getBlipId())
						{
						drawing.setObjectId(drawing.getObjectId(),
											drawing.getBlipId() - 1,
											drawing.getShapeId());
						}
					}

				numBlips--;
				}
			}


		/**
		 * Initializes the drawing data from the escher record read in
		 */
		private void initialize()
			{
			EscherRecordData er = new EscherRecordData(this,0);

			Assert.verify(er.isContainer());

			escherData = new EscherContainer(er);

			Assert.verify(escherData.getLength() == drawingData.Length);
			Assert.verify(escherData.getType() == EscherRecordType.DGG_CONTAINER);

			initialized = true;
			}

		/**
		 * Gets hold of the BStore container from the Escher data
		 *
		 * @return the BStore container
		 */
		private BStoreContainer getBStoreContainer()
			{
			if (bstoreContainer == null)
				{
				if (!initialized)
					{
					initialize();
					}

				EscherRecord[] children = escherData.getChildren();
				if (children.Length > 1 &&
					children[1].getType() == EscherRecordType.BSTORE_CONTAINER)
					{
					bstoreContainer = (BStoreContainer)children[1];
					}
				}

			return bstoreContainer;
			}

		/**
		 * Gets hold of the binary data
		 *
		 * @return the data
		 */
		public virtual byte[] getData()
			{
			return drawingData;
			}

		/**
		 * Writes the drawing group to the output file
		 *
		 * @param outputFile the file to write to
		 * @exception IOException
		 */
		public void write(File outputFile)
			{
			if (origin == Origin.WRITE)
				{
				DggContainer dggContainer = new DggContainer();

				Dgg dgg = new Dgg(numBlips + numCharts + 1,numBlips);

				dgg.addCluster(1,0);
				dgg.addCluster(numBlips + 1,0);

				dggContainer.add(dgg);

				int drawingsAdded = 0;
				BStoreContainer bstoreCont = new BStoreContainer();

				// Create a blip entry for each drawing
				foreach (object o in drawings)
					{
					if (o is Drawing)
						{
						Drawing d = (Drawing)o;
						BlipStoreEntry bse = new BlipStoreEntry(d);

						bstoreCont.add(bse);
						drawingsAdded++;
						}
					}
				if (drawingsAdded > 0)
					{
					bstoreCont.setNumBlips(drawingsAdded);
					dggContainer.add(bstoreCont);
					}

				Opt opt = new Opt();

				dggContainer.add(opt);

				SplitMenuColors splitMenuColors = new SplitMenuColors();
				dggContainer.add(splitMenuColors);

				drawingData = dggContainer.getData();
				}
			else if (origin == Origin.READ_WRITE)
				{
				DggContainer dggContainer = new DggContainer();

				Dgg dgg = new Dgg(numBlips + numCharts + 1,numBlips);

				dgg.addCluster(1,0);
				dgg.addCluster(drawingGroupId + numBlips + 1,0);

				dggContainer.add(dgg);

				BStoreContainer bstoreCont = new BStoreContainer();
				bstoreCont.setNumBlips(numBlips);

				// Create a blip entry for each drawing that was read in
				BStoreContainer readBStoreContainer = getBStoreContainer();

				if (readBStoreContainer != null)
					{
					EscherRecord[] children = readBStoreContainer.getChildren();
					for (int i = 0; i < children.Length; i++)
						{
						BlipStoreEntry bse = (BlipStoreEntry)children[i];
						bstoreCont.add(bse);
						}
					}

				// Create a blip entry for each drawing that has been added
				foreach (DrawingGroupObject dgo in drawings)
					{
					if (dgo is Drawing)
						{
						Drawing d = (Drawing)dgo;
						if (d.getOrigin() == Origin.WRITE)
							{
							BlipStoreEntry bse = new BlipStoreEntry(d);
							bstoreCont.add(bse);
							}
						}
					}

				dggContainer.add(bstoreCont);

				Opt opt = new Opt();

				opt.addProperty(191,false,false,524296);
				opt.addProperty(385,false,false,134217737);
				opt.addProperty(448,false,false,134217792);

				dggContainer.add(opt);

				SplitMenuColors splitMenuColors = new SplitMenuColors();
				dggContainer.add(splitMenuColors);

				drawingData = dggContainer.getData();
				}

			MsoDrawingGroupRecord msodg = new MsoDrawingGroupRecord(drawingData);
			outputFile.write(msodg);
			}

		/**
		 * Accessor for the number of blips in the drawing group
		 *
		 * @return the number of blips
		 */
		public int getNumberOfBlips()
			{
			return numBlips;
			}

		/**
		 * Gets the drawing data for the given blip id.  Called by the Drawing
		 * object
		 *
		 * @param blipId the blipId
		 * @return the drawing data
		 */
		public byte[] getImageData(int blipId)
			{
			numBlips = getBStoreContainer().getNumBlips();

			Assert.verify(blipId <= numBlips);
			Assert.verify(origin == Origin.READ || origin == Origin.READ_WRITE);

			// Get the blip
			EscherRecord[] children = getBStoreContainer().getChildren();
			BlipStoreEntry bse = (BlipStoreEntry)children[blipId - 1];

			return bse.getImageData();
			}

		/**
		 * Indicates that at least one of the drawings has been omitted from
		 * the worksheet

		 * @param mso the mso record
		 * @param obj the obj record
		 */
		public void setDrawingsOmitted(MsoDrawingRecord mso,ObjRecord obj)
			{
			drawingsOmitted = true;

			if (obj != null)
				{
				maxobjectId = System.Math.Max(maxobjectId,obj.getObjectId());
				}
			}

		/**
		 * Accessor for the drawingsOmitted flag
		 *
		 * @return TRUE if a drawing has been omitted, FALSE otherwise
		 */
		public bool hasDrawingsOmitted()
			{
			return drawingsOmitted;
			}

		/**
		 * Updates this with the appropriate data from the drawing group passed in
		 * This is called during the copy process:  this is first initialised as
		 * an empty object, but during the copy, the source DrawingGroup may
		 * change.  After the copy process, this method is then called to update
		 * the relevant fields.  Unfortunately, the copy process required the
		 * presence of a drawing group
		 *
		 * @param dg the drawing group containing the updated data
		 */
		public void updateData(DrawingGroup dg)
			{
			drawingsOmitted = dg.drawingsOmitted;
			maxobjectId = dg.maxobjectId;
			maxShapeId = dg.maxShapeId;
			}
		}
	}
