// Benchmark "9sym/9sym_1_9_0_esp" written by ABC on Wed Mar  8 02:36:00 2023

module \9sym/9sym_1_9_0_esp  ( 
    i0, i1, i2, i3, i4, i5, i6, i7, i8,
    zori0  );
  input  i0, i1, i2, i3, i4, i5, i6, i7, i8;
  output zori0;
  wire new_n11_, new_n12_, new_n13_, new_n14_, new_n15_, new_n16_, new_n17_,
    new_n18_, new_n19_, new_n20_, new_n21_, new_n22_, new_n23_, new_n24_,
    new_n25_, new_n26_, new_n27_, new_n28_, new_n29_, new_n30_, new_n31_,
    new_n32_, new_n33_, new_n34_, new_n35_, new_n36_, new_n37_, new_n38_,
    new_n39_, new_n40_, new_n41_, new_n42_, new_n43_, new_n44_, new_n45_,
    new_n46_, new_n47_, new_n48_, new_n49_, new_n50_, new_n51_, new_n52_,
    new_n53_, new_n54_, new_n55_, new_n56_, new_n57_, new_n58_, new_n59_,
    new_n60_, new_n61_, new_n62_, new_n63_, new_n64_, new_n65_, new_n66_,
    new_n67_, new_n68_, new_n69_, new_n70_, new_n71_, new_n72_, new_n73_,
    new_n74_, new_n75_, new_n76_, new_n77_, new_n78_, new_n79_, new_n80_,
    new_n81_, new_n82_, new_n83_, new_n84_, new_n85_, new_n86_, new_n87_,
    new_n88_, new_n89_, new_n90_, new_n91_, new_n92_, new_n93_, new_n94_,
    new_n95_, new_n96_, new_n97_, new_n98_, new_n99_, new_n100_, new_n101_,
    new_n102_, new_n103_, new_n104_, new_n105_, new_n106_, new_n107_,
    new_n108_, new_n109_, new_n110_, new_n111_, new_n112_, new_n113_,
    new_n114_, new_n115_, new_n116_, new_n117_, new_n118_, new_n119_,
    new_n120_, new_n121_, new_n122_, new_n123_, new_n124_, new_n125_,
    new_n126_, new_n127_, new_n128_, new_n129_, new_n130_, new_n131_;
  NAi21      g000(.An(i4), .B(i1), .Y(new_n11_));
  NAi21      g001(.An(i1), .B(i6), .Y(new_n12_));
  AOI210     g002(.A0(new_n12_), .A1(new_n11_), .B0(i0), .Y(new_n13_));
  NOi21      g003(.An(i8), .B(i6), .Y(new_n14_));
  NOi21      g004(.An(new_n14_), .B(i1), .Y(new_n15_));
  OAI210     g005(.A0(new_n15_), .A1(new_n13_), .B0(i5), .Y(new_n16_));
  INV        g006(.A(i1), .Y(new_n17_));
  INV        g007(.A(i7), .Y(new_n18_));
  NAi21      g008(.An(i8), .B(i4), .Y(new_n19_));
  NAi21      g009(.An(i5), .B(i8), .Y(new_n20_));
  OAI210     g010(.A0(new_n20_), .A1(new_n18_), .B0(new_n19_), .Y(new_n21_));
  NAi31      g011(.An(i4), .B(i7), .C(i1), .Y(new_n22_));
  AOI210     g012(.A0(new_n22_), .A1(new_n19_), .B0(i6), .Y(new_n23_));
  AOI210     g013(.A0(new_n21_), .A1(new_n17_), .B0(new_n23_), .Y(new_n24_));
  AOI210     g014(.A0(new_n24_), .A1(new_n16_), .B0(i3), .Y(new_n25_));
  INV        g015(.A(i0), .Y(new_n26_));
  NAi21      g016(.An(i5), .B(i6), .Y(new_n27_));
  NAi32      g017(.An(i4), .Bn(i1), .C(i3), .Y(new_n28_));
  AOI210     g018(.A0(new_n28_), .A1(new_n27_), .B0(new_n18_), .Y(new_n29_));
  NAi21      g019(.An(i8), .B(i6), .Y(new_n30_));
  NAi21      g020(.An(i4), .B(i8), .Y(new_n31_));
  NAi21      g021(.An(i7), .B(i1), .Y(new_n32_));
  AOI210     g022(.A0(new_n31_), .A1(new_n30_), .B0(new_n32_), .Y(new_n33_));
  OAI210     g023(.A0(new_n33_), .A1(new_n29_), .B0(new_n26_), .Y(new_n34_));
  INV        g024(.A(i6), .Y(new_n35_));
  INV        g025(.A(i3), .Y(new_n36_));
  NAi21      g026(.An(i0), .B(i8), .Y(new_n37_));
  NAi32      g027(.An(i8), .Bn(i4), .C(i5), .Y(new_n38_));
  AOI210     g028(.A0(new_n38_), .A1(new_n37_), .B0(new_n36_), .Y(new_n39_));
  INV        g029(.A(i4), .Y(new_n40_));
  NA2        g030(.A(i7), .B(i5), .Y(new_n41_));
  AOI210     g031(.A0(new_n41_), .A1(new_n40_), .B0(i0), .Y(new_n42_));
  OAI210     g032(.A0(new_n42_), .A1(new_n39_), .B0(new_n35_), .Y(new_n43_));
  NO3        g033(.A(i8), .B(i7), .C(i4), .Y(new_n44_));
  NO3        g034(.A(i6), .B(i5), .C(i1), .Y(new_n45_));
  OAI210     g035(.A0(new_n45_), .A1(new_n44_), .B0(i0), .Y(new_n46_));
  NO2        g036(.A(new_n35_), .B(i1), .Y(new_n47_));
  OAI220     g037(.A0(new_n20_), .A1(i4), .B0(i7), .B1(new_n36_), .Y(new_n48_));
  NO2        g038(.A(i8), .B(new_n17_), .Y(new_n49_));
  NO2        g039(.A(i5), .B(new_n36_), .Y(new_n50_));
  AOI220     g040(.A0(new_n50_), .A1(new_n49_), .B0(new_n48_), .B1(new_n47_), .Y(new_n51_));
  NA4        g041(.A(new_n51_), .B(new_n46_), .C(new_n43_), .D(new_n34_), .Y(new_n52_));
  OAI210     g042(.A0(new_n52_), .A1(new_n25_), .B0(i2), .Y(new_n53_));
  NO3        g043(.A(i6), .B(i4), .C(new_n26_), .Y(new_n54_));
  NO2        g044(.A(new_n20_), .B(i2), .Y(new_n55_));
  OAI210     g045(.A0(new_n55_), .A1(new_n54_), .B0(i3), .Y(new_n56_));
  NOi31      g046(.An(i5), .B(i4), .C(i3), .Y(new_n57_));
  NOi31      g047(.An(i8), .B(i6), .C(i2), .Y(new_n58_));
  OA210      g048(.A0(new_n58_), .A1(new_n57_), .B0(i0), .Y(new_n59_));
  NAi21      g049(.An(i4), .B(i5), .Y(new_n60_));
  NAi21      g050(.An(i3), .B(i8), .Y(new_n61_));
  NAi21      g051(.An(i2), .B(i6), .Y(new_n62_));
  AOI210     g052(.A0(new_n61_), .A1(new_n60_), .B0(new_n62_), .Y(new_n63_));
  NO2        g053(.A(new_n19_), .B(i6), .Y(new_n64_));
  NO3        g054(.A(new_n64_), .B(new_n63_), .C(new_n59_), .Y(new_n65_));
  AOI210     g055(.A0(new_n65_), .A1(new_n56_), .B0(new_n18_), .Y(new_n66_));
  INV        g056(.A(i2), .Y(new_n67_));
  NA3        g057(.A(i8), .B(i5), .C(new_n67_), .Y(new_n68_));
  OAI220     g058(.A0(new_n68_), .A1(new_n36_), .B0(new_n30_), .B1(new_n40_), .Y(new_n69_));
  INV        g059(.A(i5), .Y(new_n70_));
  NOi21      g060(.An(i4), .B(i8), .Y(new_n71_));
  NOi21      g061(.An(i8), .B(i7), .Y(new_n72_));
  AOI210     g062(.A0(new_n71_), .A1(new_n67_), .B0(new_n72_), .Y(new_n73_));
  OAI220     g063(.A0(new_n73_), .A1(new_n70_), .B0(new_n30_), .B1(i4), .Y(new_n74_));
  MUX2       g064(.S(new_n26_), .A(new_n74_), .B(new_n69_), .Y(new_n75_));
  OAI210     g065(.A0(new_n75_), .A1(new_n66_), .B0(new_n17_), .Y(new_n76_));
  NAi21      g066(.An(i5), .B(i4), .Y(new_n77_));
  NO2        g067(.A(new_n77_), .B(i8), .Y(new_n78_));
  NO2        g068(.A(i2), .B(new_n17_), .Y(new_n79_));
  NO2        g069(.A(new_n60_), .B(i7), .Y(new_n80_));
  AOI210     g070(.A0(new_n80_), .A1(new_n79_), .B0(new_n78_), .Y(new_n81_));
  NO2        g071(.A(new_n81_), .B(new_n35_), .Y(new_n82_));
  NOi21      g072(.An(i8), .B(i5), .Y(new_n83_));
  AOI210     g073(.A0(i5), .A1(new_n67_), .B0(new_n83_), .Y(new_n84_));
  INV        g074(.A(i8), .Y(new_n85_));
  AOI220     g075(.A0(new_n79_), .A1(new_n85_), .B0(new_n72_), .B1(new_n70_), .Y(new_n86_));
  NA2        g076(.A(i4), .B(i0), .Y(new_n87_));
  OAI220     g077(.A0(new_n87_), .A1(new_n86_), .B0(new_n84_), .B1(new_n22_), .Y(new_n88_));
  AOI210     g078(.A0(new_n88_), .A1(new_n35_), .B0(new_n82_), .Y(new_n89_));
  NA3        g079(.A(new_n89_), .B(new_n76_), .C(new_n53_), .Y(new_n90_));
  NOi21      g080(.An(i5), .B(i8), .Y(new_n91_));
  NO2        g081(.A(new_n85_), .B(i2), .Y(new_n92_));
  OAI210     g082(.A0(new_n92_), .A1(new_n91_), .B0(i4), .Y(new_n93_));
  NA2        g083(.A(new_n72_), .B(i5), .Y(new_n94_));
  AOI210     g084(.A0(new_n94_), .A1(new_n93_), .B0(i0), .Y(new_n95_));
  OAI210     g085(.A0(new_n91_), .A1(new_n14_), .B0(i0), .Y(new_n96_));
  NA2        g086(.A(i6), .B(new_n40_), .Y(new_n97_));
  OAI210     g087(.A0(new_n97_), .A1(new_n85_), .B0(new_n96_), .Y(new_n98_));
  AOI210     g088(.A0(new_n85_), .A1(i0), .B0(new_n83_), .Y(new_n99_));
  OAI220     g089(.A0(new_n99_), .A1(i2), .B0(new_n30_), .B1(i4), .Y(new_n100_));
  MUX2       g090(.S(new_n18_), .A(new_n100_), .B(new_n98_), .Y(new_n101_));
  OAI210     g091(.A0(new_n101_), .A1(new_n95_), .B0(i1), .Y(new_n102_));
  NO2        g092(.A(i7), .B(i4), .Y(new_n103_));
  AOI210     g093(.A0(i7), .A1(i4), .B0(i6), .Y(new_n104_));
  OAI210     g094(.A0(new_n103_), .A1(new_n68_), .B0(new_n104_), .Y(new_n105_));
  NO2        g095(.A(i4), .B(i0), .Y(new_n106_));
  OAI210     g096(.A0(new_n106_), .A1(i5), .B0(new_n68_), .Y(new_n107_));
  NA3        g097(.A(i7), .B(i6), .C(i4), .Y(new_n108_));
  NA3        g098(.A(new_n108_), .B(new_n107_), .C(new_n105_), .Y(new_n109_));
  AOI210     g099(.A0(new_n109_), .A1(new_n102_), .B0(i3), .Y(new_n110_));
  NO2        g100(.A(i6), .B(new_n26_), .Y(new_n111_));
  OAI210     g101(.A0(new_n72_), .A1(new_n111_), .B0(i4), .Y(new_n112_));
  OR2        g102(.A(new_n11_), .B(new_n35_), .Y(new_n113_));
  AOI210     g103(.A0(new_n113_), .A1(new_n112_), .B0(i5), .Y(new_n114_));
  NO2        g104(.A(i6), .B(new_n70_), .Y(new_n115_));
  AOI220     g105(.A0(new_n115_), .A1(new_n18_), .B0(new_n49_), .B1(new_n40_), .Y(new_n116_));
  NA2        g106(.A(new_n18_), .B(i5), .Y(new_n117_));
  OAI220     g107(.A0(new_n117_), .A1(new_n30_), .B0(new_n116_), .B1(new_n26_), .Y(new_n118_));
  OAI210     g108(.A0(new_n118_), .A1(new_n114_), .B0(new_n67_), .Y(new_n119_));
  NO2        g109(.A(new_n85_), .B(new_n18_), .Y(new_n120_));
  OAI210     g110(.A0(i8), .A1(i7), .B0(i1), .Y(new_n121_));
  OA220      g111(.A0(new_n121_), .A1(new_n120_), .B0(new_n97_), .B1(new_n18_), .Y(new_n122_));
  NO2        g112(.A(new_n41_), .B(i6), .Y(new_n123_));
  AOI210     g113(.A0(new_n77_), .A1(new_n38_), .B0(new_n17_), .Y(new_n124_));
  NO2        g114(.A(new_n124_), .B(new_n123_), .Y(new_n125_));
  OAI210     g115(.A0(new_n122_), .A1(i2), .B0(new_n125_), .Y(new_n126_));
  NOi21      g116(.An(new_n27_), .B(new_n111_), .Y(new_n127_));
  NA2        g117(.A(new_n115_), .B(new_n71_), .Y(new_n128_));
  OAI210     g118(.A0(new_n127_), .A1(new_n31_), .B0(new_n128_), .Y(new_n129_));
  AOI220     g119(.A0(new_n129_), .A1(new_n18_), .B0(new_n126_), .B1(new_n26_), .Y(new_n130_));
  AOI210     g120(.A0(new_n130_), .A1(new_n119_), .B0(new_n36_), .Y(new_n131_));
  OR3        g121(.A(new_n131_), .B(new_n110_), .C(new_n90_), .Y(zori0));
endmodule


