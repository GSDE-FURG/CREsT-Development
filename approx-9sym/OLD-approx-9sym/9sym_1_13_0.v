// Benchmark "9sym/9sym_1_13_0_esp" written by ABC on Wed Mar  8 02:19:07 2023

module \9sym/9sym_1_13_0_esp  ( 
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
    new_n120_;
  INV        g000(.A(i2), .Y(new_n11_));
  INV        g001(.A(i6), .Y(new_n12_));
  NOi21      g002(.An(i7), .B(i4), .Y(new_n13_));
  NOi21      g003(.An(i5), .B(i7), .Y(new_n14_));
  NOi21      g004(.An(new_n14_), .B(i8), .Y(new_n15_));
  OAI210     g005(.A0(new_n15_), .A1(new_n13_), .B0(i3), .Y(new_n16_));
  NOi21      g006(.An(i5), .B(i4), .Y(new_n17_));
  NA2        g007(.A(new_n17_), .B(i1), .Y(new_n18_));
  AOI210     g008(.A0(new_n18_), .A1(new_n16_), .B0(new_n12_), .Y(new_n19_));
  NA2        g009(.A(new_n17_), .B(i7), .Y(new_n20_));
  INV        g010(.A(i8), .Y(new_n21_));
  NO2        g011(.A(new_n21_), .B(i7), .Y(new_n22_));
  INV        g012(.A(i5), .Y(new_n23_));
  NO2        g013(.A(new_n12_), .B(new_n23_), .Y(new_n24_));
  NA2        g014(.A(i3), .B(i1), .Y(new_n25_));
  NO2        g015(.A(new_n25_), .B(i8), .Y(new_n26_));
  AOI210     g016(.A0(new_n24_), .A1(new_n22_), .B0(new_n26_), .Y(new_n27_));
  OAI210     g017(.A0(new_n27_), .A1(i0), .B0(new_n20_), .Y(new_n28_));
  OAI210     g018(.A0(new_n28_), .A1(new_n19_), .B0(new_n11_), .Y(new_n29_));
  INV        g019(.A(i1), .Y(new_n30_));
  NOi31      g020(.An(i4), .B(i8), .C(i2), .Y(new_n31_));
  OAI210     g021(.A0(new_n31_), .A1(new_n22_), .B0(i0), .Y(new_n32_));
  INV        g022(.A(i0), .Y(new_n33_));
  NOi21      g023(.An(i8), .B(i2), .Y(new_n34_));
  NA3        g024(.A(new_n34_), .B(i3), .C(new_n33_), .Y(new_n35_));
  AOI210     g025(.A0(new_n35_), .A1(new_n32_), .B0(new_n23_), .Y(new_n36_));
  NA3        g026(.A(new_n13_), .B(i3), .C(i2), .Y(new_n37_));
  NOi21      g027(.An(i4), .B(i8), .Y(new_n38_));
  NA2        g028(.A(new_n38_), .B(i6), .Y(new_n39_));
  AO210      g029(.A0(new_n39_), .A1(new_n37_), .B0(i0), .Y(new_n40_));
  NOi31      g030(.An(i7), .B(i5), .C(i2), .Y(new_n41_));
  NA3        g031(.A(new_n41_), .B(i8), .C(i3), .Y(new_n42_));
  NA2        g032(.A(i6), .B(i2), .Y(new_n43_));
  INV        g033(.A(i7), .Y(new_n44_));
  NA2        g034(.A(new_n44_), .B(i3), .Y(new_n45_));
  INV        g035(.A(i4), .Y(new_n46_));
  NA3        g036(.A(i8), .B(new_n23_), .C(new_n46_), .Y(new_n47_));
  AO210      g037(.A0(new_n47_), .A1(new_n45_), .B0(new_n43_), .Y(new_n48_));
  NA3        g038(.A(new_n48_), .B(new_n42_), .C(new_n40_), .Y(new_n49_));
  OAI210     g039(.A0(new_n49_), .A1(new_n36_), .B0(new_n30_), .Y(new_n50_));
  INV        g040(.A(i3), .Y(new_n51_));
  NOi31      g041(.An(i8), .B(i7), .C(i4), .Y(new_n52_));
  AOI220     g042(.A0(new_n52_), .A1(i6), .B0(new_n21_), .B1(i1), .Y(new_n53_));
  OAI210     g043(.A0(new_n43_), .A1(new_n44_), .B0(new_n25_), .Y(new_n54_));
  NA2        g044(.A(new_n21_), .B(i6), .Y(new_n55_));
  NA3        g045(.A(new_n34_), .B(new_n44_), .C(i3), .Y(new_n56_));
  AOI210     g046(.A0(new_n56_), .A1(new_n55_), .B0(new_n46_), .Y(new_n57_));
  AOI210     g047(.A0(new_n54_), .A1(new_n33_), .B0(new_n57_), .Y(new_n58_));
  OAI210     g048(.A0(new_n53_), .A1(new_n51_), .B0(new_n58_), .Y(new_n59_));
  OAI210     g049(.A0(new_n21_), .A1(i4), .B0(new_n55_), .Y(new_n60_));
  NO3        g050(.A(i7), .B(new_n30_), .C(i0), .Y(new_n61_));
  NOi21      g051(.An(i3), .B(i4), .Y(new_n62_));
  NO2        g052(.A(i8), .B(new_n23_), .Y(new_n63_));
  AOI220     g053(.A0(new_n63_), .A1(new_n62_), .B0(new_n61_), .B1(new_n60_), .Y(new_n64_));
  NA2        g054(.A(new_n46_), .B(i0), .Y(new_n65_));
  OAI220     g055(.A0(new_n65_), .A1(new_n55_), .B0(new_n64_), .B1(new_n11_), .Y(new_n66_));
  AOI210     g056(.A0(new_n59_), .A1(new_n23_), .B0(new_n66_), .Y(new_n67_));
  NOi21      g057(.An(i4), .B(i5), .Y(new_n68_));
  OA210      g058(.A0(new_n68_), .A1(new_n14_), .B0(new_n11_), .Y(new_n69_));
  OA210      g059(.A0(new_n69_), .A1(new_n52_), .B0(i3), .Y(new_n70_));
  NOi31      g060(.An(i8), .B(i7), .C(i3), .Y(new_n71_));
  OAI210     g061(.A0(new_n71_), .A1(new_n31_), .B0(i1), .Y(new_n72_));
  NOi21      g062(.An(i7), .B(i1), .Y(new_n73_));
  OAI210     g063(.A0(new_n62_), .A1(new_n34_), .B0(new_n73_), .Y(new_n74_));
  NOi21      g064(.An(i4), .B(i7), .Y(new_n75_));
  NA3        g065(.A(new_n75_), .B(i8), .C(new_n23_), .Y(new_n76_));
  NA3        g066(.A(new_n76_), .B(new_n74_), .C(new_n72_), .Y(new_n77_));
  OAI210     g067(.A0(new_n77_), .A1(new_n70_), .B0(i0), .Y(new_n78_));
  NO2        g068(.A(new_n13_), .B(new_n30_), .Y(new_n79_));
  AOI210     g069(.A0(i8), .A1(i5), .B0(i1), .Y(new_n80_));
  NO3        g070(.A(new_n80_), .B(new_n79_), .C(i3), .Y(new_n81_));
  OAI220     g071(.A0(i8), .A1(i3), .B0(i5), .B1(i1), .Y(new_n82_));
  NA2        g072(.A(new_n82_), .B(i0), .Y(new_n83_));
  AOI210     g073(.A0(i8), .A1(i3), .B0(i4), .Y(new_n84_));
  OAI210     g074(.A0(new_n84_), .A1(i0), .B0(new_n83_), .Y(new_n85_));
  OAI210     g075(.A0(new_n85_), .A1(new_n81_), .B0(i2), .Y(new_n86_));
  NAi21      g076(.An(i8), .B(i4), .Y(new_n87_));
  AOI210     g077(.A0(i5), .A1(new_n11_), .B0(new_n68_), .Y(new_n88_));
  OAI220     g078(.A0(new_n88_), .A1(i3), .B0(new_n87_), .B1(i1), .Y(new_n89_));
  NOi21      g079(.An(i7), .B(i5), .Y(new_n90_));
  AOI220     g080(.A0(new_n90_), .A1(new_n46_), .B0(i5), .B1(new_n33_), .Y(new_n91_));
  NA2        g081(.A(i8), .B(i1), .Y(new_n92_));
  NA3        g082(.A(new_n38_), .B(new_n14_), .C(i3), .Y(new_n93_));
  OAI210     g083(.A0(new_n92_), .A1(new_n91_), .B0(new_n93_), .Y(new_n94_));
  AOI210     g084(.A0(new_n89_), .A1(i7), .B0(new_n94_), .Y(new_n95_));
  NA3        g085(.A(new_n95_), .B(new_n86_), .C(new_n78_), .Y(new_n96_));
  NA3        g086(.A(i5), .B(i4), .C(new_n33_), .Y(new_n97_));
  AOI210     g087(.A0(i7), .A1(new_n11_), .B0(new_n14_), .Y(new_n98_));
  OAI210     g088(.A0(new_n98_), .A1(new_n33_), .B0(new_n97_), .Y(new_n99_));
  OAI210     g089(.A0(new_n52_), .A1(new_n41_), .B0(i6), .Y(new_n100_));
  NO2        g090(.A(new_n34_), .B(new_n17_), .Y(new_n101_));
  OAI210     g091(.A0(i4), .A1(i2), .B0(new_n33_), .Y(new_n102_));
  OAI210     g092(.A0(new_n102_), .A1(new_n101_), .B0(new_n100_), .Y(new_n103_));
  AOI210     g093(.A0(new_n99_), .A1(new_n21_), .B0(new_n103_), .Y(new_n104_));
  NAi21      g094(.An(i7), .B(i4), .Y(new_n105_));
  NAi31      g095(.An(i1), .B(i6), .C(i5), .Y(new_n106_));
  AOI210     g096(.A0(new_n106_), .A1(new_n105_), .B0(i0), .Y(new_n107_));
  NAi31      g097(.An(i5), .B(i8), .C(i7), .Y(new_n108_));
  AOI210     g098(.A0(new_n108_), .A1(new_n87_), .B0(i1), .Y(new_n109_));
  OA210      g099(.A0(new_n109_), .A1(new_n107_), .B0(i2), .Y(new_n110_));
  NO2        g100(.A(new_n17_), .B(i6), .Y(new_n111_));
  NOi31      g101(.An(new_n73_), .B(new_n111_), .C(new_n101_), .Y(new_n112_));
  NA2        g102(.A(new_n75_), .B(i8), .Y(new_n113_));
  NA2        g103(.A(i5), .B(new_n11_), .Y(new_n114_));
  AOI210     g104(.A0(new_n46_), .A1(i0), .B0(new_n75_), .Y(new_n115_));
  NA2        g105(.A(i6), .B(new_n23_), .Y(new_n116_));
  OAI220     g106(.A0(new_n116_), .A1(new_n115_), .B0(new_n114_), .B1(new_n113_), .Y(new_n117_));
  NO3        g107(.A(new_n117_), .B(new_n112_), .C(new_n110_), .Y(new_n118_));
  OAI210     g108(.A0(new_n104_), .A1(new_n30_), .B0(new_n118_), .Y(new_n119_));
  AOI220     g109(.A0(new_n119_), .A1(new_n51_), .B0(new_n96_), .B1(new_n12_), .Y(new_n120_));
  NA4        g110(.A(new_n120_), .B(new_n67_), .C(new_n50_), .D(new_n29_), .Y(zori0));
endmodule


