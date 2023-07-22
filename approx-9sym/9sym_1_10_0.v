// Benchmark "9sym/9sym_1_10_0_esp" written by ABC on Wed Mar  8 02:18:09 2023

module \9sym/9sym_1_10_0_esp  ( 
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
    new_n120_, new_n121_, new_n122_, new_n123_;
  INV        g000(.A(i1), .Y(new_n11_));
  INV        g001(.A(i3), .Y(new_n12_));
  INV        g002(.A(i7), .Y(new_n13_));
  NA2        g003(.A(i8), .B(i6), .Y(new_n14_));
  NO2        g004(.A(new_n14_), .B(i4), .Y(new_n15_));
  INV        g005(.A(i5), .Y(new_n16_));
  INV        g006(.A(i8), .Y(new_n17_));
  NA2        g007(.A(new_n14_), .B(i0), .Y(new_n18_));
  AOI210     g008(.A0(new_n17_), .A1(new_n16_), .B0(new_n18_), .Y(new_n19_));
  OA210      g009(.A0(new_n19_), .A1(new_n15_), .B0(new_n13_), .Y(new_n20_));
  INV        g010(.A(i4), .Y(new_n21_));
  NA3        g011(.A(i7), .B(i6), .C(new_n21_), .Y(new_n22_));
  NO2        g012(.A(new_n21_), .B(i0), .Y(new_n23_));
  NA2        g013(.A(new_n23_), .B(i5), .Y(new_n24_));
  AOI210     g014(.A0(new_n24_), .A1(new_n22_), .B0(i8), .Y(new_n25_));
  OAI210     g015(.A0(new_n25_), .A1(new_n20_), .B0(new_n12_), .Y(new_n26_));
  INV        g016(.A(i2), .Y(new_n27_));
  NAi21      g017(.An(i4), .B(i3), .Y(new_n28_));
  XO2        g018(.A(i3), .B(i0), .Y(new_n29_));
  NAi21      g019(.An(new_n29_), .B(new_n28_), .Y(new_n30_));
  AOI210     g020(.A0(new_n29_), .A1(new_n13_), .B0(i8), .Y(new_n31_));
  NA2        g021(.A(new_n31_), .B(new_n30_), .Y(new_n32_));
  NOi21      g022(.An(i6), .B(i5), .Y(new_n33_));
  NOi21      g023(.An(new_n33_), .B(i4), .Y(new_n34_));
  NO3        g024(.A(new_n17_), .B(i7), .C(i0), .Y(new_n35_));
  OAI210     g025(.A0(new_n35_), .A1(new_n34_), .B0(i3), .Y(new_n36_));
  NOi21      g026(.An(i7), .B(i5), .Y(new_n37_));
  NO2        g027(.A(new_n17_), .B(i3), .Y(new_n38_));
  OAI210     g028(.A0(new_n37_), .A1(new_n23_), .B0(new_n38_), .Y(new_n39_));
  INV        g029(.A(i6), .Y(new_n40_));
  NO2        g030(.A(new_n40_), .B(new_n16_), .Y(new_n41_));
  NO2        g031(.A(i7), .B(i4), .Y(new_n42_));
  NOi21      g032(.An(i4), .B(i8), .Y(new_n43_));
  INV        g033(.A(i0), .Y(new_n44_));
  NO2        g034(.A(i6), .B(new_n44_), .Y(new_n45_));
  AOI220     g035(.A0(new_n45_), .A1(new_n43_), .B0(new_n42_), .B1(new_n41_), .Y(new_n46_));
  NA4        g036(.A(new_n46_), .B(new_n39_), .C(new_n36_), .D(new_n32_), .Y(new_n47_));
  NOi21      g037(.An(i5), .B(i6), .Y(new_n48_));
  NAi21      g038(.An(i5), .B(i4), .Y(new_n49_));
  NAi21      g039(.An(i4), .B(i5), .Y(new_n50_));
  OAI210     g040(.A0(new_n50_), .A1(i8), .B0(new_n49_), .Y(new_n51_));
  AOI220     g041(.A0(new_n51_), .A1(i3), .B0(new_n48_), .B1(i8), .Y(new_n52_));
  NOi21      g042(.An(i7), .B(i6), .Y(new_n53_));
  NO2        g043(.A(new_n17_), .B(i4), .Y(new_n54_));
  NA3        g044(.A(new_n54_), .B(new_n53_), .C(new_n16_), .Y(new_n55_));
  OAI210     g045(.A0(new_n52_), .A1(i0), .B0(new_n55_), .Y(new_n56_));
  AOI210     g046(.A0(new_n47_), .A1(new_n27_), .B0(new_n56_), .Y(new_n57_));
  AOI210     g047(.A0(new_n57_), .A1(new_n26_), .B0(new_n11_), .Y(new_n58_));
  OR2        g048(.A(new_n28_), .B(new_n13_), .Y(new_n59_));
  NA3        g049(.A(i6), .B(i5), .C(new_n12_), .Y(new_n60_));
  AOI210     g050(.A0(new_n60_), .A1(new_n59_), .B0(i1), .Y(new_n61_));
  OR2        g051(.A(new_n50_), .B(new_n11_), .Y(new_n62_));
  NA2        g052(.A(new_n13_), .B(i4), .Y(new_n63_));
  AOI210     g053(.A0(new_n63_), .A1(new_n62_), .B0(i3), .Y(new_n64_));
  NO2        g054(.A(i8), .B(new_n40_), .Y(new_n65_));
  NO2        g055(.A(i7), .B(new_n11_), .Y(new_n66_));
  OA210      g056(.A0(new_n65_), .A1(new_n54_), .B0(new_n66_), .Y(new_n67_));
  NO2        g057(.A(i6), .B(new_n21_), .Y(new_n68_));
  AO210      g058(.A0(new_n37_), .A1(i6), .B0(new_n68_), .Y(new_n69_));
  NO4        g059(.A(new_n69_), .B(new_n67_), .C(new_n64_), .D(new_n61_), .Y(new_n70_));
  OAI210     g060(.A0(new_n40_), .A1(new_n11_), .B0(new_n43_), .Y(new_n71_));
  NOi21      g061(.An(i8), .B(i1), .Y(new_n72_));
  OAI210     g062(.A0(new_n48_), .A1(new_n37_), .B0(new_n72_), .Y(new_n73_));
  AOI210     g063(.A0(new_n73_), .A1(new_n71_), .B0(i3), .Y(new_n74_));
  NO2        g064(.A(i8), .B(i6), .Y(new_n75_));
  NA2        g065(.A(i5), .B(i3), .Y(new_n76_));
  NOi21      g066(.An(new_n75_), .B(new_n76_), .Y(new_n77_));
  AN2        g067(.A(new_n72_), .B(new_n33_), .Y(new_n78_));
  OA210      g068(.A0(new_n78_), .A1(new_n77_), .B0(new_n21_), .Y(new_n79_));
  NO4        g069(.A(new_n75_), .B(i7), .C(new_n12_), .D(i1), .Y(new_n80_));
  NO3        g070(.A(i6), .B(i5), .C(i1), .Y(new_n81_));
  AOI210     g071(.A0(new_n42_), .A1(new_n17_), .B0(new_n81_), .Y(new_n82_));
  NO2        g072(.A(i4), .B(i3), .Y(new_n83_));
  NOi21      g073(.An(i3), .B(i5), .Y(new_n84_));
  AOI220     g074(.A0(new_n84_), .A1(new_n17_), .B0(new_n83_), .B1(new_n53_), .Y(new_n85_));
  OAI220     g075(.A0(new_n85_), .A1(new_n11_), .B0(new_n82_), .B1(new_n44_), .Y(new_n86_));
  NO4        g076(.A(new_n86_), .B(new_n80_), .C(new_n79_), .D(new_n74_), .Y(new_n87_));
  OA210      g077(.A0(new_n70_), .A1(i0), .B0(new_n87_), .Y(new_n88_));
  NO2        g078(.A(new_n12_), .B(i2), .Y(new_n89_));
  AOI220     g079(.A0(new_n89_), .A1(new_n68_), .B0(new_n83_), .B1(i6), .Y(new_n90_));
  AOI210     g080(.A0(new_n53_), .A1(new_n12_), .B0(new_n65_), .Y(new_n91_));
  OAI220     g081(.A0(new_n91_), .A1(new_n21_), .B0(new_n90_), .B1(new_n44_), .Y(new_n92_));
  NA2        g082(.A(i3), .B(new_n44_), .Y(new_n93_));
  NO2        g083(.A(new_n53_), .B(new_n15_), .Y(new_n94_));
  NOi21      g084(.An(new_n53_), .B(i4), .Y(new_n95_));
  OAI210     g085(.A0(new_n95_), .A1(new_n12_), .B0(i5), .Y(new_n96_));
  OAI220     g086(.A0(new_n96_), .A1(new_n94_), .B0(new_n93_), .B1(new_n22_), .Y(new_n97_));
  AOI220     g087(.A0(new_n97_), .A1(new_n27_), .B0(new_n92_), .B1(new_n16_), .Y(new_n98_));
  OAI210     g088(.A0(new_n88_), .A1(new_n27_), .B0(new_n98_), .Y(new_n99_));
  NAi21      g089(.An(i2), .B(i8), .Y(new_n100_));
  AOI210     g090(.A0(new_n100_), .A1(new_n28_), .B0(new_n44_), .Y(new_n101_));
  OAI210     g091(.A0(new_n101_), .A1(new_n43_), .B0(new_n40_), .Y(new_n102_));
  OAI210     g092(.A0(new_n100_), .A1(new_n40_), .B0(new_n50_), .Y(new_n103_));
  AO220      g093(.A0(new_n84_), .A1(i8), .B0(i5), .B1(new_n21_), .Y(new_n104_));
  AOI220     g094(.A0(new_n104_), .A1(new_n27_), .B0(new_n103_), .B1(new_n12_), .Y(new_n105_));
  AOI210     g095(.A0(new_n105_), .A1(new_n102_), .B0(new_n13_), .Y(new_n106_));
  NO2        g096(.A(new_n100_), .B(new_n76_), .Y(new_n107_));
  AO210      g097(.A0(new_n65_), .A1(i4), .B0(new_n107_), .Y(new_n108_));
  AOI220     g098(.A0(new_n43_), .A1(new_n27_), .B0(i8), .B1(new_n13_), .Y(new_n109_));
  NA3        g099(.A(new_n17_), .B(i6), .C(new_n21_), .Y(new_n110_));
  OAI210     g100(.A0(new_n109_), .A1(new_n16_), .B0(new_n110_), .Y(new_n111_));
  MUX2       g101(.S(new_n44_), .A(new_n111_), .B(new_n108_), .Y(new_n112_));
  OAI210     g102(.A0(new_n112_), .A1(new_n106_), .B0(new_n11_), .Y(new_n113_));
  NO2        g103(.A(i5), .B(i3), .Y(new_n114_));
  NOi31      g104(.An(new_n76_), .B(new_n114_), .C(i2), .Y(new_n115_));
  AOI210     g105(.A0(new_n45_), .A1(new_n16_), .B0(new_n115_), .Y(new_n116_));
  AOI210     g106(.A0(new_n114_), .A1(i6), .B0(new_n77_), .Y(new_n117_));
  OAI210     g107(.A0(new_n116_), .A1(new_n17_), .B0(new_n117_), .Y(new_n118_));
  NO3        g108(.A(new_n65_), .B(new_n45_), .C(new_n33_), .Y(new_n119_));
  AOI210     g109(.A0(i5), .A1(new_n27_), .B0(new_n54_), .Y(new_n120_));
  NO3        g110(.A(new_n120_), .B(new_n119_), .C(new_n12_), .Y(new_n121_));
  AOI210     g111(.A0(new_n118_), .A1(i4), .B0(new_n121_), .Y(new_n122_));
  OAI210     g112(.A0(new_n122_), .A1(i7), .B0(new_n113_), .Y(new_n123_));
  OR3        g113(.A(new_n123_), .B(new_n99_), .C(new_n58_), .Y(zori0));
endmodule


