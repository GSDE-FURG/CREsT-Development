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

using CSharpJExcel.Jxl.Common;
using System;
using CSharpJExcel.Jxl.Write.Biff;


namespace CSharpJExcel.Jxl.Biff.Drawing
	{
	/**
	 * Contains the various biff records used to insert a drawing into a
	 * worksheet
	 */
	public class Drawing : DrawingGroupObject,Image
		{
		/**
		 * The logger
		 */
		//  private static Logger logger = Logger.getLogger(Drawing.class);

		/**
		 * The spContainer that was read in
		 */
		private EscherContainer readSpContainer;

		/**
		 * The MsoDrawingRecord associated with the drawing
		 */
		private MsoDrawingRecord msoDrawingRecord;

		/**
		 * The ObjRecord associated with the drawing
		 */
		private ObjRecord objRecord;

		/**
		 * Initialized flag
		 */
		private bool initialized = false;

		/**
		 * The file containing the image
		 */
		private System.IO.FileInfo imageFile;

		/**
		 * The raw image data, used instead of an image file
		 */
		private byte[] imageData;

		/**
		 * The object id, assigned by the drawing group
		 */
		private uint objectId;

		/**
		 * The blip id
		 */
		private int blipId;

		/**
		 * The column position of the image
		 */
		private double x;

		/**
		 * The row position of the image
		 */
		private double y;

		/**
		 * The width of the image in cells
		 */
		private double width;

		/**
		 * The height of the image in cells
		 */
		private double height;

		/**
		 * The number of places this drawing is referenced
		 */
		private int referenceCount;

		/**
		 * The top level escher container
		 */
		private EscherContainer escherData;

		/**
		 * Where this image came from (read, written or a copy)
		 */
		private Origin origin;

		/**
		 * The drawing group for all the images
		 */
		private DrawingGroup drawingGroup;

		/**
		 * The drawing data
		 */
		private DrawingData drawingData;

		/**
		 * The type of this drawing object
		 */
		private ShapeType type;

		/**
		 * The shape id
		 */
		private int shapeId;

		/**
		 * The drawing position on the sheet
		 */
		private int drawingNumber;

		/**
		 * A reference to the sheet containing this drawing.  Used to calculate
		 * the drawing dimensions in pixels
		 */
		private Sheet sheet;

		/**
		 * Reader for the raw image data
		 */
		private PNGReader pngReader;

		/**
		 * The client anchor properties
		 */
		private ImageAnchorProperties imageAnchorProperties;

		// Enumeration type for the image anchor properties
		public sealed class ImageAnchorProperties
			{
			private int value;
			private static ImageAnchorProperties[] o = new ImageAnchorProperties[0];

			internal ImageAnchorProperties(int v)
				{
				value = v;

				ImageAnchorProperties[] oldArray = o;
				o = new ImageAnchorProperties[oldArray.Length + 1];
				System.Array.Copy(oldArray,0,o,0,oldArray.Length);
				o[oldArray.Length] = this;
				}

			public int getValue()
				{
				return value;
				}

			public static ImageAnchorProperties getImageAnchorProperties(int val)
				{
				ImageAnchorProperties iap = MOVE_AND_SIZE_WITH_CELLS;
				int pos = 0;
				while (pos < o.Length)
					{
					if (o[pos].getValue() == val)
						{
						iap = o[pos];
						break;
						}
					else
						{
						pos++;
						}
					}
				return iap;
				}
			}

		// The image anchor properties
		public static readonly ImageAnchorProperties MOVE_AND_SIZE_WITH_CELLS = new ImageAnchorProperties(1);
		public static readonly ImageAnchorProperties MOVE_WITH_CELLS = new ImageAnchorProperties(2);
		public static readonly ImageAnchorProperties NO_MOVE_OR_SIZE_WITH_CELLS = new ImageAnchorProperties(3);

		/**
		 * The default font size for columns
		 */
		private const double DEFAULT_FONT_SIZE = 10;

		/**
		 * Constructor used when reading images
		 *
		 * @param mso the drawing record
		 * @param obj the object record
		 * @param dd the drawing data for all drawings on this sheet
		 * @param dg the drawing group
		 */
		public Drawing(MsoDrawingRecord mso,
					   ObjRecord obj,
					   DrawingData dd,
					   DrawingGroup dg,
					   Sheet s)
			{
			drawingGroup = dg;
			msoDrawingRecord = mso;
			drawingData = dd;
			objRecord = obj;
			sheet = s;
			initialized = false;
			origin = Origin.READ;
			drawingData.addData(msoDrawingRecord.getData());
			drawingNumber = drawingData.getNumDrawings() - 1;
			drawingGroup.addDrawing(this);

			Assert.verify(mso != null && obj != null);

			initialize();
			}

		/**
		 * Copy constructor used to copy drawings from read to write
		 *
		 * @param dgo the drawing group object
		 * @param dg the drawing group
		 */
		public Drawing(DrawingGroupObject dgo,DrawingGroup dg)
			{
			Drawing d = (Drawing)dgo;
			Assert.verify(d.origin == Origin.READ);
			msoDrawingRecord = d.msoDrawingRecord;
			objRecord = d.objRecord;
			initialized = false;
			origin = Origin.READ;
			drawingData = d.drawingData;
			drawingGroup = dg;
			drawingNumber = d.drawingNumber;
			drawingGroup.addDrawing(this);
			}

		/**
		 * Constructor invoked when writing the images
		 *
		 * @param x the column
		 * @param y the row
		 * @param w the width in cells
		 * @param h the height in cells
		 * @param image the image file
		 */
		public Drawing(double x,
					   double y,
					   double w,
					   double h,
					   System.IO.FileInfo image)
			{
			imageFile = image;
			initialized = true;
			origin = Origin.WRITE;
			this.x = x;
			this.y = y;
			this.width = w;
			this.height = h;
			referenceCount = 1;
			imageAnchorProperties = MOVE_WITH_CELLS;
			type = ShapeType.PICTURE_FRAME;
			}

		/**
		 * Constructor invoked when writing the images
		 *
		 * @param x the column
		 * @param y the row
		 * @param w the width in cells
		 * @param h the height in cells
		 * @param image the image data
		 */
		public Drawing(double x,
					   double y,
					   double w,
					   double h,
					   byte[] image)
			{
			imageData = image;
			initialized = true;
			origin = Origin.WRITE;
			this.x = x;
			this.y = y;
			this.width = w;
			this.height = h;
			referenceCount = 1;
			imageAnchorProperties = MOVE_WITH_CELLS;
			type = ShapeType.PICTURE_FRAME;
			}

		/**
		 * Initializes the member variables from the Escher stream data
		 */
		private void initialize()
			{
			readSpContainer = drawingData.getSpContainer(drawingNumber);
			Assert.verify(readSpContainer != null);

			EscherRecord[] children = readSpContainer.getChildren();

			Sp sp = (Sp)readSpContainer.getChildren()[0];
			shapeId = sp.getShapeId();
			objectId = objRecord.getObjectId();
			type = ShapeType.getType(sp.getShapeType());

			if (type == ShapeType.UNKNOWN)
				{
				//logger.warn("Unknown shape type");
				}

			Opt opt = (Opt)readSpContainer.getChildren()[1];

			if (opt.getProperty(260) != null)
				blipId = opt.getProperty(260).value;

			if (opt.getProperty(261) != null)
				imageFile = new System.IO.FileInfo(opt.getProperty(261).StringValue);
			else
				{
				if (type == ShapeType.PICTURE_FRAME)
					{
					//logger.warn("no filename property for drawing");
					imageFile = new System.IO.FileInfo(blipId.ToString().Trim());
					}
				}

			ClientAnchor clientAnchor = null;
			for (int i = 0; i < children.Length && clientAnchor == null; i++)
				{
				if (children[i].getType() == EscherRecordType.CLIENT_ANCHOR)
					clientAnchor = (ClientAnchor)children[i];
				}

			if (clientAnchor == null)
				{
				//logger.warn("client anchor not found");
				}
			else
				{
				x = clientAnchor.getX1();
				y = clientAnchor.getY1();
				width = clientAnchor.getX2() - x;
				height = clientAnchor.getY2() - y;
				imageAnchorProperties = ImageAnchorProperties.getImageAnchorProperties(clientAnchor.getProperties());
				}

			if (blipId == 0)
				{
				//logger.warn("linked drawings are not supported");
				}

			initialized = true;
			}

		/**
		 * Accessor for the image file
		 *
		 * @return the image file
		 */
		public virtual System.IO.FileInfo getImageFile()
			{
			return imageFile;
			}

		/**
		 * Accessor for the image file path.  Normally this is the absolute path
		 * of a file on the directory system, but if this drawing was constructed
		 * using an byte[] then the blip id is returned
		 *
		 * @return the image file path, or the blip id
		 */
		public virtual string getImageFilePath()
			{
			if (imageFile == null)
				{
				// return the blip id, if it exists
				return blipId != 0 ? blipId.ToString() : "__new__image__";
				}

			return imageFile.FullName;
			}

		/**
		 * Sets the object id.  Invoked by the drawing group when the object is
		 * added to id
		 *
		 * @param objid the object id
		 * @param bip the blip id
		 * @param sid the shape id
		 */
		public virtual void setObjectId(uint objid, int bip, int sid)
			{
			objectId = objid;
			blipId = bip;
			shapeId = sid;

			if (origin == Origin.READ)
				{
				origin = Origin.READ_WRITE;
				}
			}

		/**
		 * Accessor for the object id
		 *
		 * @return the object id
		 */
		public virtual uint getObjectId()
			{
			if (!initialized)
				initialize();

			return objectId;
			}

		/**
		 * Accessor for the shape id
		 *
		 * @return the shape id
		 */
		public virtual int getShapeId()
			{
			if (!initialized)
				initialize();

			return shapeId;
			}

		/**
		 * Accessor for the blip id
		 *
		 * @return the blip id
		 */
		public virtual int getBlipId()
			{
			if (!initialized)
				initialize();

			return blipId;
			}

		/**
		 * Gets the drawing record which was read in
		 *
		 * @return the drawing record
		 */
		public virtual MsoDrawingRecord getMsoDrawingRecord()
			{
			return msoDrawingRecord;
			}

		/**
		 * Creates the main Sp container for the drawing
		 *
		 * @return the SP container
		 */
		public virtual EscherContainer getSpContainer()
			{
			if (!initialized)
				initialize();

			if (origin == Origin.READ)
				return getReadSpContainer();

			SpContainer spContainer = new SpContainer();
			Sp sp = new Sp(type,shapeId,2560);
			spContainer.add(sp);
			Opt opt = new Opt();
			opt.addProperty(260,true,false,blipId);

			if (type == ShapeType.PICTURE_FRAME)
				{
				string filePath = imageFile != null ? imageFile.FullName : string.Empty;
				opt.addProperty(261,true,true,filePath.Length * 2,filePath);
				opt.addProperty(447,false,false,65536);
				opt.addProperty(959,false,false,524288);
				spContainer.add(opt);
				}

			ClientAnchor clientAnchor = new ClientAnchor
			  (x,y,x + width,y + height,
			   imageAnchorProperties.getValue());
			spContainer.add(clientAnchor);
			ClientData clientData = new ClientData();
			spContainer.add(clientData);

			return spContainer;
			}

		/**
		 * Sets the drawing group for this drawing.  Called by the drawing group
		 * when this drawing is added to it
		 *
		 * @param dg the drawing group
		 */
		public virtual void setDrawingGroup(DrawingGroup dg)
			{
			drawingGroup = dg;
			}

		/**
		 * Accessor for the drawing group
		 *
		 * @return the drawing group
		 */
		public virtual DrawingGroup getDrawingGroup()
			{
			return drawingGroup;
			}

		/**
		 * Gets the origin of this drawing
		 *
		 * @return where this drawing came from
		 */
		public virtual Origin getOrigin()
			{
			return origin;
			}

		/**
		 * Accessor for the reference count on this drawing
		 *
		 * @return the reference count
		 */
		public virtual int getReferenceCount()
			{
			return referenceCount;
			}

		/**
		 * Sets the new reference count on the drawing
		 *
		 * @param r the new reference count
		 */
		public virtual void setReferenceCount(int r)
			{
			referenceCount = r;
			}

		/**
		 * Accessor for the column of this drawing
		 *
		 * @return the column
		 */
		public virtual double getX()
			{
			if (!initialized)
				{
				initialize();
				}
			return x;
			}

		/**
		 * Sets the column position of this drawing
		 *
		 * @param x the column
		 */
		public virtual void setX(double x)
			{
			if (origin == Origin.READ)
				{
				if (!initialized)
					{
					initialize();
					}
				origin = Origin.READ_WRITE;
				}

			this.x = x;
			}

		/**
		 * Accessor for the row of this drawing
		 *
		 * @return the row
		 */
		public virtual double getY()
			{
			if (!initialized)
				{
				initialize();
				}

			return y;
			}

		/**
		 * Accessor for the row of the drawing
		 *
		 * @param y the row
		 */
		public virtual void setY(double y)
			{
			if (origin == Origin.READ)
				{
				if (!initialized)
					{
					initialize();
					}
				origin = Origin.READ_WRITE;
				}

			this.y = y;
			}


		/**
		 * Accessor for the width of this drawing
		 *
		 * @return the number of columns spanned by this image
		 */
		public virtual double getWidth()
			{
			if (!initialized)
				{
				initialize();
				}

			return width;
			}

		/**
		 * Accessor for the width
		 *
		 * @param w the number of columns to span
		 */
		public virtual void setWidth(double w)
			{
			if (origin == Origin.READ)
				{
				if (!initialized)
					{
					initialize();
					}
				origin = Origin.READ_WRITE;
				}

			width = w;
			}

		/**
		 * Accessor for the height of this drawing
		 *
		 * @return the number of rows spanned by this image
		 */
		public virtual double getHeight()
			{
			if (!initialized)
				{
				initialize();
				}

			return height;
			}

		/**
		 * Accessor for the height of this drawing
		 *
		 * @param h the number of rows spanned by this image
		 */
		public virtual void setHeight(double h)
			{
			if (origin == Origin.READ)
				{
				if (!initialized)
					initialize();
				origin = Origin.READ_WRITE;
				}

			height = h;
			}


		/**
		 * Gets the SpContainer that was read in
		 *
		 * @return the read sp container
		 */
		private EscherContainer getReadSpContainer()
			{
			if (!initialized)
				{
				initialize();
				}

			return readSpContainer;
			}

		/**
		 * Accessor for the image data
		 *
		 * @return the image data
		 */
		public virtual byte[] getImageData()
			{
			Assert.verify(origin == Origin.READ || origin == Origin.READ_WRITE);

			if (!initialized)
				initialize();

			return drawingGroup.getImageData(blipId);
			}


		/// <summary>
		/// CML: Created shared binary reader to ensure the file is being read correctly.
		/// </summary>
		/// <param name="File"></param>
		/// <returns>An array containing data.  Array is sized for the data from the file.</returns>
		public static byte[] readBinaryFile(System.IO.FileInfo File)
			{
			byte[] data = new byte[(int)File.Length];
			System.IO.BinaryReader fis = null;
			try
				{
				fis = new System.IO.BinaryReader(new System.IO.FileStream(File.FullName, System.IO.FileMode.Open, System.IO.FileAccess.Read));
				fis.Read(data, 0, data.Length);
				}
			finally
				{
				if (fis != null)
					fis.Close();
				}

			return data;
			}


		/**
		 * Accessor for the image data
		 *
		 * @return the image data
		 */
		public virtual byte[] getImageBytes()
			{
			if (origin == Origin.READ || origin == Origin.READ_WRITE)
				return getImageData();

			Assert.verify(origin == Origin.WRITE);

			if (imageFile == null)
				{
				Assert.verify(imageData != null);
				return imageData;
				}

			return Drawing.readBinaryFile(imageFile);
			}

		/**
		 * Accessor for the type
		 *
		 * @return the type
		 */
		public virtual ShapeType getType()
			{
			return type;
			}

		/**
		 * Writes any other records associated with this drawing group object
		 *
		 * @param outputFile the output file
		 * @exception IOException
		 */
		public virtual void writeAdditionalRecords(File outputFile)
			{
			if (origin == Origin.READ)
				{
				outputFile.write(objRecord);
				return;
				}

			// Create the obj record
			ObjRecord objrec = new ObjRecord(objectId,ObjRecord.PICTURE);
			outputFile.write(objrec);
			}

		/**
		 * Writes any records that need to be written after all the drawing group
		 * objects have been written
		 * Does nothing here
		 *
		 * @param outputFile the output file
		 */
		public virtual void writeTailRecords(File outputFile)
			{
			// does nothing
			}

		/**
		 * Interface method
		 *
		 * @return the column number at which the image is positioned
		 */
		public virtual double getColumn()
			{
			return getX();
			}

		/**
		 * Interface method
		 *
		 * @return the row number at which the image is positions
		 */
		public virtual double getRow()
			{
			return getY();
			}

		/**
		 * Accessor for the first drawing on the sheet.  This is used when
		 * copying unmodified sheets to indicate that this drawing contains
		 * the first time Escher gubbins
		 *
		 * @return TRUE if this MSORecord is the first drawing on the sheet
		 */
		public virtual bool isFirst()
			{
			return msoDrawingRecord.isFirst();
			}

		/**
		 * Queries whether this object is a form object.  Form objects have their
		 * drawings records spread over TXO and CONTINUE records and
		 * require special handling
		 *
		 * @return TRUE if this is a form object, FALSE otherwise
		 */
		public virtual bool isFormObject()
			{
			return false;
			}

		/**
		 * Removes a row
		 *
		 * @param r the row to be removed
		 */
		public virtual void removeRow(int r)
			{
			if (y > r)
				{
				setY(r);
				}
			}

		/**
		 * Accessor for the image dimensions.  See technotes for Bill's explanation
		 * of the calculation logic
		 *
		 * @return  approximate drawing size in pixels
		 */
		private double getWidthInPoints()
			{
			if (sheet == null)
				{
				//logger.warn("calculating image width:  sheet is null");
				return 0;
				}

			// The start and end row numbers
			int firstCol = (int)x;
			int lastCol = (int)Math.Ceiling(x + width) - 1;

			// **** MAGIC NUMBER ALERT ***
			// multiply the point size of the font by 0.59 to give the point size
			// I know of no explanation for this yet, other than that it seems to
			// give the right answer

			// Get the width of the image within the first col, allowing for 
			// fractional offsets
			CellView cellView = sheet.getColumnView(firstCol);
			int firstColWidth = cellView.getSize();
			double firstColImageWidth = (1 - (x - firstCol)) * firstColWidth;
			double pointSize = (cellView.getFormat() != null) ?
			  cellView.getFormat().getFont().getPointSize() : DEFAULT_FONT_SIZE;
			double firstColWidthInPoints = firstColImageWidth * 0.59 * pointSize / 256;

			// Get the height of the image within the last row, allowing for
			// fractional offsets
			int lastColWidth = 0;
			double lastColImageWidth = 0;
			double lastColWidthInPoints = 0;
			if (lastCol != firstCol)
				{
				cellView = sheet.getColumnView(lastCol);
				lastColWidth = cellView.getSize();
				lastColImageWidth = (x + width - lastCol) * lastColWidth;
				pointSize = (cellView.getFormat() != null) ?
				  cellView.getFormat().getFont().getPointSize() : DEFAULT_FONT_SIZE;
				lastColWidthInPoints = lastColImageWidth * 0.59 * pointSize / 256;
				}

			// Now get all the columns in between
			double newWidth = 0;
			for (int i = 0; i < lastCol - firstCol - 1; i++)
				{
				cellView = sheet.getColumnView(firstCol + 1 + i);
				pointSize = (cellView.getFormat() != null) ? cellView.getFormat().getFont().getPointSize() : DEFAULT_FONT_SIZE;
				newWidth += cellView.getSize() * 0.59 * pointSize / 256;
				}

			// Add on the first and last row contributions to get the height in twips
			double widthInPoints = newWidth +
			  firstColWidthInPoints + lastColWidthInPoints;

			return widthInPoints;
			}

		/**
		 * Accessor for the image dimensions.  See technotes for Bill's explanation
		 * of the calculation logic
		 *
		 * @return approximate drawing size in pixels
		 */
		private double getHeightInPoints()
			{
			if (sheet == null)
				{
				//logger.warn("calculating image height:  sheet is null");
				return 0;
				}

			// The start and end row numbers
			int firstRow = (int)y;
			int lastRow = (int)Math.Ceiling(y + height) - 1;

			// Get the height of the image within the first row, allowing for 
			// fractional offsets
			int firstRowHeight = sheet.getRowView(firstRow).getSize();
			double firstRowImageHeight = (1 - (y - firstRow)) * firstRowHeight;

			// Get the height of the image within the last row, allowing for
			// fractional offsets
			int lastRowHeight = 0;
			double lastRowImageHeight = 0;
			if (lastRow != firstRow)
				{
				lastRowHeight = sheet.getRowView(lastRow).getSize();
				lastRowImageHeight = (y + height - lastRow) * lastRowHeight;
				}

			// Now get all the rows in between
			double newHeight = 0;
			for (int i = 0; i < lastRow - firstRow - 1; i++)
				newHeight += sheet.getRowView(firstRow + 1 + i).getSize();

			// Add on the first and last row contributions to get the height in twips
			double heightInTwips = newHeight + firstRowHeight + lastRowHeight;

			// Now divide by the magic number to converts twips into pixels and 
			// return the value
			double heightInPoints = heightInTwips / 20.0;

			return heightInPoints;
			}

		/**
		 * Get the width of this image as rendered within Excel
		 *
		 * @param unit the unit of measurement
		 * @return the width of the image within Excel
		 */
		public virtual double getWidth(LengthUnit unit)
			{
			double widthInPoints = getWidthInPoints();
			return widthInPoints * LengthConverter.getConversionFactor(LengthUnit.POINTS,unit);
			}

		/**
		 * Get the height of this image as rendered within Excel
		 *
		 * @param unit the unit of measurement
		 * @return the height of the image within Excel
		 */
		public virtual double getHeight(LengthUnit unit)
			{
			double heightInPoints = getHeightInPoints();
			return heightInPoints * LengthConverter.getConversionFactor
			  (LengthUnit.POINTS,unit);
			}

		/**
		 * Gets the width of the image.  Note that this is the width of the 
		 * underlying image, and does not take into account any size manipulations
		 * that may have occurred when the image was added into Excel
		 *
		 * @return the image width in pixels
		 */
		public virtual int getImageWidth()
			{
			return getPngReader().getWidth();
			}

		/**
		 * Gets the height of the image.  Note that this is the height of the 
		 * underlying image, and does not take into account any size manipulations
		 * that may have occurred when the image was added into Excel
		 *
		 * @return the image width in pixels
		 */
		public virtual int getImageHeight()
			{
			return getPngReader().getHeight();
			}


		/**
		 * Gets the horizontal resolution of the image, if that information
		 * is available.
		 *
		 * @return the number of dots per unit specified, if available, 0 otherwise
		 */
		public virtual double getHorizontalResolution(LengthUnit unit)
			{
			int res = getPngReader().getHorizontalResolution();
			return res / LengthConverter.getConversionFactor(LengthUnit.METRES,unit);
			}

		/**
		 * Gets the vertical resolution of the image, if that information
		 * is available.
		 *
		 * @return the number of dots per unit specified, if available, 0 otherwise
		 */
		public virtual double getVerticalResolution(LengthUnit unit)
			{
			int res = getPngReader().getVerticalResolution();
			return res / LengthConverter.getConversionFactor(LengthUnit.METRES,unit);
			}

		private PNGReader getPngReader()
			{
			if (pngReader != null)
				return pngReader;

			byte[] imdata = null;
			if (origin == Origin.READ || origin == Origin.READ_WRITE)
				imdata = getImageData();
			else
				{
				try
					{
					imdata = getImageBytes();
					}
				catch (System.IO.IOException e)
					{
					//logger.warn("Could not read image file");
					imdata = new byte[0];
					}
				}

			pngReader = new PNGReader(imdata);
			pngReader.read();
			return pngReader;
			}

		/**
		 * Accessor for the anchor properties
		 */
		public virtual void setImageAnchor(ImageAnchorProperties iap)
			{
			imageAnchorProperties = iap;

			if (origin == Origin.READ)
				origin = Origin.READ_WRITE;
			}

		/**
		 * Accessor for the anchor properties
		 */
		public virtual ImageAnchorProperties getImageAnchor()
			{
			if (!initialized)
				initialize();

			return imageAnchorProperties;
			}
		}
	}



