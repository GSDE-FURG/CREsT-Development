// Benchmark "9sym/9sym_1_8_0_esp" written by ABC on Wed Mar  8 02:34:58 2023

module \9sym/9sym_1_8_0_esp  ( 
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
  INV        g000(.A(i6), .Y(new_n11_));
  INV        g001(.A(i0), .Y(new_n12_));
  INV        g002(.A(i5), .Y(new_n13_));
  INV        g003(.A(i4), .Y(new_n14_));
  INV        g004(.A(i2), .Y(new_n15_));
  NOi21      g005(.An(i8), .B(i7), .Y(new_n16_));
  AOI210     g006(.A0(i3), .A1(new_n15_), .B0(new_n16_), .Y(new_n17_));
  INV        g007(.A(i1), .Y(new_n18_));
  NA2        g008(.A(i2), .B(new_n18_), .Y(new_n19_));
  OAI210     g009(.A0(new_n17_), .A1(new_n14_), .B0(new_n19_), .Y(new_n20_));
  INV        g010(.A(i8), .Y(new_n21_));
  NAi21      g011(.An(i4), .B(i3), .Y(new_n22_));
  OA210      g012(.A0(new_n21_), .A1(i2), .B0(new_n22_), .Y(new_n23_));
  NA2        g013(.A(i7), .B(new_n18_), .Y(new_n24_));
  NAi21      g014(.An(i2), .B(i5), .Y(new_n25_));
  OA210      g015(.A0(new_n21_), .A1(i4), .B0(new_n25_), .Y(new_n26_));
  INV        g016(.A(i7), .Y(new_n27_));
  NA2        g017(.A(new_n27_), .B(i3), .Y(new_n28_));
  OAI220     g018(.A0(new_n28_), .A1(new_n26_), .B0(new_n24_), .B1(new_n23_), .Y(new_n29_));
  AOI210     g019(.A0(new_n20_), .A1(new_n13_), .B0(new_n29_), .Y(new_n30_));
  AOI220     g020(.A0(i8), .A1(i5), .B0(i4), .B1(i2), .Y(new_n31_));
  NA2        g021(.A(i7), .B(i5), .Y(new_n32_));
  NO2        g022(.A(i3), .B(i2), .Y(new_n33_));
  OAI220     g023(.A0(new_n33_), .A1(new_n32_), .B0(new_n31_), .B1(new_n18_), .Y(new_n34_));
  NA2        g024(.A(i8), .B(new_n13_), .Y(new_n35_));
  NA2        g025(.A(new_n35_), .B(new_n25_), .Y(new_n36_));
  NA2        g026(.A(new_n14_), .B(i1), .Y(new_n37_));
  NO2        g027(.A(new_n37_), .B(new_n27_), .Y(new_n38_));
  AOI220     g028(.A0(new_n38_), .A1(new_n36_), .B0(new_n34_), .B1(new_n12_), .Y(new_n39_));
  OAI210     g029(.A0(new_n30_), .A1(new_n12_), .B0(new_n39_), .Y(new_n40_));
  NO2        g030(.A(i5), .B(i3), .Y(new_n41_));
  AOI210     g031(.A0(new_n21_), .A1(new_n18_), .B0(new_n41_), .Y(new_n42_));
  NOi21      g032(.An(i6), .B(i4), .Y(new_n43_));
  NOi21      g033(.An(i7), .B(i6), .Y(new_n44_));
  AOI220     g034(.A0(new_n44_), .A1(i4), .B0(new_n43_), .B1(i0), .Y(new_n45_));
  NOi21      g035(.An(i2), .B(i7), .Y(new_n46_));
  NOi21      g036(.An(i7), .B(i2), .Y(new_n47_));
  AOI210     g037(.A0(new_n47_), .A1(new_n13_), .B0(new_n46_), .Y(new_n48_));
  NOi21      g038(.An(i4), .B(i7), .Y(new_n49_));
  NA3        g039(.A(new_n49_), .B(new_n13_), .C(new_n15_), .Y(new_n50_));
  OAI210     g040(.A0(new_n48_), .A1(i1), .B0(new_n50_), .Y(new_n51_));
  NAi21      g041(.An(i7), .B(i5), .Y(new_n52_));
  NO3        g042(.A(new_n52_), .B(i1), .C(new_n12_), .Y(new_n53_));
  AOI210     g043(.A0(new_n51_), .A1(i3), .B0(new_n53_), .Y(new_n54_));
  OAI220     g044(.A0(new_n54_), .A1(new_n21_), .B0(new_n45_), .B1(new_n42_), .Y(new_n55_));
  AOI210     g045(.A0(new_n40_), .A1(new_n11_), .B0(new_n55_), .Y(new_n56_));
  AOI210     g046(.A0(i8), .A1(i5), .B0(i2), .Y(new_n57_));
  OAI210     g047(.A0(new_n14_), .A1(new_n15_), .B0(new_n18_), .Y(new_n58_));
  NO3        g048(.A(new_n58_), .B(new_n57_), .C(new_n46_), .Y(new_n59_));
  NA2        g049(.A(i6), .B(new_n14_), .Y(new_n60_));
  NAi21      g050(.An(i2), .B(i7), .Y(new_n61_));
  AOI220     g051(.A0(new_n16_), .A1(new_n15_), .B0(new_n13_), .B1(i4), .Y(new_n62_));
  OAI220     g052(.A0(new_n62_), .A1(new_n18_), .B0(new_n61_), .B1(new_n60_), .Y(new_n63_));
  OAI210     g053(.A0(new_n63_), .A1(new_n59_), .B0(i3), .Y(new_n64_));
  NO2        g054(.A(i4), .B(new_n18_), .Y(new_n65_));
  OAI220     g055(.A0(new_n21_), .A1(new_n14_), .B0(new_n27_), .B1(new_n11_), .Y(new_n66_));
  AO220      g056(.A0(new_n66_), .A1(new_n13_), .B0(new_n65_), .B1(new_n16_), .Y(new_n67_));
  NA2        g057(.A(new_n67_), .B(i2), .Y(new_n68_));
  AOI210     g058(.A0(new_n68_), .A1(new_n64_), .B0(i0), .Y(new_n69_));
  NA2        g059(.A(new_n32_), .B(i1), .Y(new_n70_));
  OAI220     g060(.A0(new_n70_), .A1(new_n41_), .B0(new_n24_), .B1(new_n13_), .Y(new_n71_));
  AOI210     g061(.A0(new_n28_), .A1(new_n19_), .B0(new_n35_), .Y(new_n72_));
  AOI210     g062(.A0(new_n71_), .A1(new_n15_), .B0(new_n72_), .Y(new_n73_));
  OAI220     g063(.A0(new_n73_), .A1(i4), .B0(new_n28_), .B1(new_n19_), .Y(new_n74_));
  AOI210     g064(.A0(new_n74_), .A1(i6), .B0(new_n69_), .Y(new_n75_));
  NA2        g065(.A(i5), .B(new_n14_), .Y(new_n76_));
  AOI210     g066(.A0(new_n76_), .A1(new_n61_), .B0(i0), .Y(new_n77_));
  NO2        g067(.A(i5), .B(new_n15_), .Y(new_n78_));
  OAI210     g068(.A0(new_n78_), .A1(new_n77_), .B0(i3), .Y(new_n79_));
  NO3        g069(.A(new_n13_), .B(new_n14_), .C(i3), .Y(new_n80_));
  NO2        g070(.A(i7), .B(new_n11_), .Y(new_n81_));
  AO210      g071(.A0(new_n81_), .A1(i2), .B0(new_n80_), .Y(new_n82_));
  NO3        g072(.A(new_n60_), .B(new_n27_), .C(i3), .Y(new_n83_));
  AOI210     g073(.A0(new_n82_), .A1(new_n12_), .B0(new_n83_), .Y(new_n84_));
  AOI210     g074(.A0(new_n84_), .A1(new_n79_), .B0(new_n18_), .Y(new_n85_));
  AOI210     g075(.A0(new_n52_), .A1(new_n61_), .B0(i3), .Y(new_n86_));
  NAi21      g076(.An(i6), .B(i4), .Y(new_n87_));
  AOI210     g077(.A0(new_n87_), .A1(new_n22_), .B0(i2), .Y(new_n88_));
  OAI210     g078(.A0(new_n88_), .A1(new_n86_), .B0(i1), .Y(new_n89_));
  OAI220     g079(.A0(i7), .A1(i4), .B0(i6), .B1(i3), .Y(new_n90_));
  OAI220     g080(.A0(new_n25_), .A1(new_n14_), .B0(new_n11_), .B1(i3), .Y(new_n91_));
  AOI220     g081(.A0(new_n91_), .A1(new_n18_), .B0(new_n90_), .B1(i2), .Y(new_n92_));
  AOI210     g082(.A0(new_n92_), .A1(new_n89_), .B0(new_n12_), .Y(new_n93_));
  INV        g083(.A(i3), .Y(new_n94_));
  OA210      g084(.A0(new_n11_), .A1(i2), .B0(new_n87_), .Y(new_n95_));
  NOi21      g085(.An(i5), .B(i6), .Y(new_n96_));
  NO2        g086(.A(new_n96_), .B(i4), .Y(new_n97_));
  OAI210     g087(.A0(new_n13_), .A1(new_n14_), .B0(i2), .Y(new_n98_));
  OA220      g088(.A0(new_n98_), .A1(new_n97_), .B0(new_n95_), .B1(new_n52_), .Y(new_n99_));
  OAI210     g089(.A0(i1), .A1(i0), .B0(i5), .Y(new_n100_));
  NA3        g090(.A(new_n100_), .B(i6), .C(i4), .Y(new_n101_));
  OAI210     g091(.A0(new_n99_), .A1(new_n94_), .B0(new_n101_), .Y(new_n102_));
  OR2        g092(.A(new_n102_), .B(new_n93_), .Y(new_n103_));
  OAI210     g093(.A0(new_n103_), .A1(new_n85_), .B0(new_n21_), .Y(new_n104_));
  AOI220     g094(.A0(i7), .A1(new_n13_), .B0(i4), .B1(new_n12_), .Y(new_n105_));
  NO3        g095(.A(new_n49_), .B(new_n44_), .C(new_n43_), .Y(new_n106_));
  OAI220     g096(.A0(new_n106_), .A1(new_n13_), .B0(new_n105_), .B1(new_n18_), .Y(new_n107_));
  NA2        g097(.A(new_n107_), .B(new_n15_), .Y(new_n108_));
  AOI210     g098(.A0(i7), .A1(new_n13_), .B0(new_n96_), .Y(new_n109_));
  NA3        g099(.A(i7), .B(i6), .C(new_n15_), .Y(new_n110_));
  OAI210     g100(.A0(new_n109_), .A1(new_n15_), .B0(new_n110_), .Y(new_n111_));
  AOI220     g101(.A0(new_n111_), .A1(new_n18_), .B0(new_n81_), .B1(new_n65_), .Y(new_n112_));
  AOI210     g102(.A0(new_n112_), .A1(new_n108_), .B0(new_n21_), .Y(new_n113_));
  NO2        g103(.A(new_n11_), .B(i1), .Y(new_n114_));
  OR3        g104(.A(new_n114_), .B(new_n49_), .C(new_n65_), .Y(new_n115_));
  NO2        g105(.A(new_n13_), .B(i0), .Y(new_n116_));
  AOI220     g106(.A0(new_n116_), .A1(new_n115_), .B0(new_n44_), .B1(new_n65_), .Y(new_n117_));
  NO2        g107(.A(i5), .B(new_n14_), .Y(new_n118_));
  NA3        g108(.A(new_n16_), .B(new_n11_), .C(i1), .Y(new_n119_));
  OAI210     g109(.A0(new_n76_), .A1(new_n24_), .B0(new_n119_), .Y(new_n120_));
  AOI220     g110(.A0(new_n120_), .A1(i0), .B0(new_n81_), .B1(new_n118_), .Y(new_n121_));
  OAI210     g111(.A0(new_n117_), .A1(new_n15_), .B0(new_n121_), .Y(new_n122_));
  OAI210     g112(.A0(new_n122_), .A1(new_n113_), .B0(new_n94_), .Y(new_n123_));
  NA4        g113(.A(new_n123_), .B(new_n104_), .C(new_n75_), .D(new_n56_), .Y(zori0));
endmodule


