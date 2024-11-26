// Benchmark "9sym/9sym_1_11_0_esp" written by ABC on Wed Mar  8 02:18:39 2023

module \9sym/9sym_1_11_0_esp  ( 
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
    new_n120_, new_n121_;
  INV        g000(.A(i7), .Y(new_n11_));
  INV        g001(.A(i6), .Y(new_n12_));
  NA2        g002(.A(new_n12_), .B(i5), .Y(new_n13_));
  INV        g003(.A(i2), .Y(new_n14_));
  NA2        g004(.A(i3), .B(new_n14_), .Y(new_n15_));
  INV        g005(.A(i1), .Y(new_n16_));
  NO2        g006(.A(i8), .B(new_n16_), .Y(new_n17_));
  NAi21      g007(.An(new_n15_), .B(new_n17_), .Y(new_n18_));
  AO210      g008(.A0(new_n18_), .A1(new_n13_), .B0(new_n11_), .Y(new_n19_));
  INV        g009(.A(i3), .Y(new_n20_));
  NO2        g010(.A(new_n11_), .B(new_n20_), .Y(new_n21_));
  NAi21      g011(.An(i2), .B(i8), .Y(new_n22_));
  NO2        g012(.A(i4), .B(i3), .Y(new_n23_));
  NO3        g013(.A(new_n23_), .B(new_n22_), .C(new_n21_), .Y(new_n24_));
  NA2        g014(.A(i5), .B(new_n20_), .Y(new_n25_));
  NOi21      g015(.An(i4), .B(i8), .Y(new_n26_));
  AOI210     g016(.A0(new_n11_), .A1(i6), .B0(new_n26_), .Y(new_n27_));
  NAi21      g017(.An(i4), .B(i8), .Y(new_n28_));
  XO2        g018(.A(i5), .B(i4), .Y(new_n29_));
  NA3        g019(.A(new_n29_), .B(new_n28_), .C(i3), .Y(new_n30_));
  OAI210     g020(.A0(new_n27_), .A1(new_n25_), .B0(new_n30_), .Y(new_n31_));
  OAI210     g021(.A0(new_n31_), .A1(new_n24_), .B0(i1), .Y(new_n32_));
  AOI210     g022(.A0(new_n32_), .A1(new_n19_), .B0(i0), .Y(new_n33_));
  INV        g023(.A(i8), .Y(new_n34_));
  NO3        g024(.A(new_n34_), .B(i7), .C(i4), .Y(new_n35_));
  OAI210     g025(.A0(new_n35_), .A1(new_n17_), .B0(i3), .Y(new_n36_));
  NA2        g026(.A(i7), .B(i4), .Y(new_n37_));
  INV        g027(.A(i0), .Y(new_n38_));
  INV        g028(.A(i4), .Y(new_n39_));
  AOI210     g029(.A0(new_n39_), .A1(new_n38_), .B0(i3), .Y(new_n40_));
  AOI210     g030(.A0(new_n40_), .A1(new_n37_), .B0(new_n26_), .Y(new_n41_));
  AO210      g031(.A0(new_n41_), .A1(new_n36_), .B0(i5), .Y(new_n42_));
  AOI220     g032(.A0(i8), .A1(i5), .B0(i7), .B1(i3), .Y(new_n43_));
  XN2        g033(.A(i8), .B(i7), .Y(new_n44_));
  NA2        g034(.A(new_n20_), .B(i1), .Y(new_n45_));
  OAI220     g035(.A0(new_n45_), .A1(new_n44_), .B0(new_n43_), .B1(i2), .Y(new_n46_));
  INV        g036(.A(i5), .Y(new_n47_));
  NO4        g037(.A(new_n15_), .B(i8), .C(i7), .D(new_n47_), .Y(new_n48_));
  AOI210     g038(.A0(new_n46_), .A1(new_n39_), .B0(new_n48_), .Y(new_n49_));
  AOI210     g039(.A0(new_n49_), .A1(new_n42_), .B0(new_n12_), .Y(new_n50_));
  NA2        g040(.A(new_n34_), .B(i3), .Y(new_n51_));
  NO2        g041(.A(i5), .B(i3), .Y(new_n52_));
  NA2        g042(.A(i5), .B(i3), .Y(new_n53_));
  NAi21      g043(.An(new_n22_), .B(new_n53_), .Y(new_n54_));
  OAI220     g044(.A0(new_n54_), .A1(new_n52_), .B0(new_n51_), .B1(new_n13_), .Y(new_n55_));
  NAi21      g045(.An(i5), .B(i7), .Y(new_n56_));
  INV        g046(.A(new_n56_), .Y(new_n57_));
  NO2        g047(.A(i6), .B(i3), .Y(new_n58_));
  AOI220     g048(.A0(new_n58_), .A1(new_n57_), .B0(new_n55_), .B1(new_n11_), .Y(new_n59_));
  NO2        g049(.A(i6), .B(i4), .Y(new_n60_));
  NA2        g050(.A(new_n56_), .B(new_n13_), .Y(new_n61_));
  NO2        g051(.A(i3), .B(i2), .Y(new_n62_));
  AOI220     g052(.A0(new_n62_), .A1(new_n61_), .B0(new_n60_), .B1(new_n57_), .Y(new_n63_));
  NA2        g053(.A(i8), .B(i1), .Y(new_n64_));
  OAI220     g054(.A0(new_n64_), .A1(new_n63_), .B0(new_n59_), .B1(new_n39_), .Y(new_n65_));
  NO3        g055(.A(new_n65_), .B(new_n50_), .C(new_n33_), .Y(new_n66_));
  NA2        g056(.A(new_n12_), .B(i4), .Y(new_n67_));
  NA2        g057(.A(i7), .B(new_n20_), .Y(new_n68_));
  AOI210     g058(.A0(new_n68_), .A1(new_n67_), .B0(i2), .Y(new_n69_));
  NO2        g059(.A(new_n25_), .B(i7), .Y(new_n70_));
  OAI210     g060(.A0(new_n70_), .A1(new_n69_), .B0(new_n34_), .Y(new_n71_));
  NOi21      g061(.An(i8), .B(i7), .Y(new_n72_));
  NA2        g062(.A(new_n58_), .B(new_n72_), .Y(new_n73_));
  AOI210     g063(.A0(new_n73_), .A1(new_n71_), .B0(new_n16_), .Y(new_n74_));
  NO2        g064(.A(i7), .B(new_n20_), .Y(new_n75_));
  OAI210     g065(.A0(new_n47_), .A1(i2), .B0(new_n28_), .Y(new_n76_));
  AOI210     g066(.A0(i3), .A1(new_n14_), .B0(new_n72_), .Y(new_n77_));
  NO3        g067(.A(new_n77_), .B(i5), .C(new_n39_), .Y(new_n78_));
  AOI210     g068(.A0(new_n76_), .A1(new_n75_), .B0(new_n78_), .Y(new_n79_));
  NAi21      g069(.An(i4), .B(i1), .Y(new_n80_));
  OAI220     g070(.A0(new_n80_), .A1(new_n15_), .B0(new_n79_), .B1(i6), .Y(new_n81_));
  OAI210     g071(.A0(new_n81_), .A1(new_n74_), .B0(i0), .Y(new_n82_));
  NAi21      g072(.An(i1), .B(i6), .Y(new_n83_));
  AOI210     g073(.A0(new_n83_), .A1(new_n80_), .B0(i0), .Y(new_n84_));
  NO3        g074(.A(new_n34_), .B(i6), .C(i1), .Y(new_n85_));
  OAI210     g075(.A0(new_n85_), .A1(new_n84_), .B0(i5), .Y(new_n86_));
  NAi21      g076(.An(i8), .B(i4), .Y(new_n87_));
  OAI210     g077(.A0(new_n56_), .A1(new_n34_), .B0(new_n87_), .Y(new_n88_));
  OAI210     g078(.A0(new_n80_), .A1(new_n11_), .B0(new_n87_), .Y(new_n89_));
  AOI220     g079(.A0(new_n89_), .A1(new_n12_), .B0(new_n88_), .B1(new_n16_), .Y(new_n90_));
  AOI210     g080(.A0(new_n90_), .A1(new_n86_), .B0(i3), .Y(new_n91_));
  NO2        g081(.A(new_n34_), .B(i6), .Y(new_n92_));
  NO3        g082(.A(new_n11_), .B(i4), .C(i1), .Y(new_n93_));
  OAI210     g083(.A0(new_n93_), .A1(new_n92_), .B0(i3), .Y(new_n94_));
  AOI210     g084(.A0(new_n56_), .A1(i6), .B0(new_n60_), .Y(new_n95_));
  OAI210     g085(.A0(i8), .A1(new_n12_), .B0(new_n28_), .Y(new_n96_));
  NO2        g086(.A(i7), .B(new_n16_), .Y(new_n97_));
  AOI210     g087(.A0(new_n97_), .A1(new_n96_), .B0(new_n95_), .Y(new_n98_));
  AOI210     g088(.A0(new_n98_), .A1(new_n94_), .B0(i0), .Y(new_n99_));
  NO2        g089(.A(i5), .B(i1), .Y(new_n100_));
  NA2        g090(.A(new_n100_), .B(new_n12_), .Y(new_n101_));
  NA3        g091(.A(new_n34_), .B(new_n11_), .C(new_n39_), .Y(new_n102_));
  AOI210     g092(.A0(new_n102_), .A1(new_n101_), .B0(new_n38_), .Y(new_n103_));
  NO2        g093(.A(new_n28_), .B(i5), .Y(new_n104_));
  NO2        g094(.A(new_n104_), .B(new_n75_), .Y(new_n105_));
  NO2        g095(.A(new_n60_), .B(new_n47_), .Y(new_n106_));
  OR3        g096(.A(new_n106_), .B(new_n100_), .C(new_n51_), .Y(new_n107_));
  OAI210     g097(.A0(new_n105_), .A1(new_n83_), .B0(new_n107_), .Y(new_n108_));
  NO4        g098(.A(new_n108_), .B(new_n103_), .C(new_n99_), .D(new_n91_), .Y(new_n109_));
  OAI210     g099(.A0(i4), .A1(new_n20_), .B0(new_n22_), .Y(new_n110_));
  AOI210     g100(.A0(new_n110_), .A1(i0), .B0(new_n26_), .Y(new_n111_));
  NO2        g101(.A(new_n58_), .B(new_n22_), .Y(new_n112_));
  AOI220     g102(.A0(new_n112_), .A1(new_n53_), .B0(new_n23_), .B1(i5), .Y(new_n113_));
  OAI210     g103(.A0(new_n111_), .A1(i6), .B0(new_n113_), .Y(new_n114_));
  OAI220     g104(.A0(new_n53_), .A1(new_n22_), .B0(new_n87_), .B1(new_n12_), .Y(new_n115_));
  AOI210     g105(.A0(new_n26_), .A1(new_n14_), .B0(new_n72_), .Y(new_n116_));
  NA3        g106(.A(new_n34_), .B(i6), .C(new_n39_), .Y(new_n117_));
  OAI210     g107(.A0(new_n116_), .A1(new_n47_), .B0(new_n117_), .Y(new_n118_));
  MUX2       g108(.S(new_n38_), .A(new_n118_), .B(new_n115_), .Y(new_n119_));
  AOI210     g109(.A0(new_n114_), .A1(i7), .B0(new_n119_), .Y(new_n120_));
  OA220      g110(.A0(new_n120_), .A1(i1), .B0(new_n109_), .B1(new_n14_), .Y(new_n121_));
  NA3        g111(.A(new_n121_), .B(new_n82_), .C(new_n66_), .Y(zori0));
endmodule


