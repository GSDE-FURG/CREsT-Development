// Benchmark "9sym/9sym_1_12_0_esp" written by ABC on Wed Mar  8 02:18:53 2023

module \9sym/9sym_1_12_0_esp  ( 
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
    new_n114_, new_n115_, new_n116_, new_n117_;
  INV        g000(.A(i1), .Y(new_n11_));
  INV        g001(.A(i3), .Y(new_n12_));
  INV        g002(.A(i8), .Y(new_n13_));
  NAi21      g003(.An(i8), .B(i5), .Y(new_n14_));
  OAI210     g004(.A0(new_n13_), .A1(i2), .B0(new_n14_), .Y(new_n15_));
  NA2        g005(.A(new_n15_), .B(i4), .Y(new_n16_));
  INV        g006(.A(i7), .Y(new_n17_));
  NA3        g007(.A(new_n17_), .B(i6), .C(i5), .Y(new_n18_));
  AOI210     g008(.A0(new_n18_), .A1(new_n16_), .B0(i0), .Y(new_n19_));
  NAi21      g009(.An(i8), .B(i6), .Y(new_n20_));
  NO2        g010(.A(new_n20_), .B(i4), .Y(new_n21_));
  NO2        g011(.A(i8), .B(i0), .Y(new_n22_));
  AN2        g012(.A(i8), .B(i5), .Y(new_n23_));
  NO3        g013(.A(new_n23_), .B(new_n22_), .C(i2), .Y(new_n24_));
  OAI210     g014(.A0(new_n24_), .A1(new_n21_), .B0(i7), .Y(new_n25_));
  OAI210     g015(.A0(new_n13_), .A1(i6), .B0(new_n14_), .Y(new_n26_));
  NO2        g016(.A(new_n13_), .B(i4), .Y(new_n27_));
  AOI220     g017(.A0(new_n27_), .A1(i6), .B0(new_n26_), .B1(i0), .Y(new_n28_));
  OAI210     g018(.A0(new_n28_), .A1(i7), .B0(new_n25_), .Y(new_n29_));
  OAI210     g019(.A0(new_n29_), .A1(new_n19_), .B0(new_n12_), .Y(new_n30_));
  INV        g020(.A(i4), .Y(new_n31_));
  NA2        g021(.A(new_n22_), .B(i3), .Y(new_n32_));
  NAi31      g022(.An(i5), .B(i8), .C(i7), .Y(new_n33_));
  OAI210     g023(.A0(new_n33_), .A1(i6), .B0(new_n32_), .Y(new_n34_));
  NA2        g024(.A(i8), .B(i7), .Y(new_n35_));
  AOI210     g025(.A0(i8), .A1(i0), .B0(i5), .Y(new_n36_));
  NO2        g026(.A(i2), .B(i0), .Y(new_n37_));
  AOI210     g027(.A0(new_n37_), .A1(new_n35_), .B0(new_n36_), .Y(new_n38_));
  INV        g028(.A(i0), .Y(new_n39_));
  INV        g029(.A(i2), .Y(new_n40_));
  NO2        g030(.A(i8), .B(new_n31_), .Y(new_n41_));
  AOI210     g031(.A0(new_n41_), .A1(new_n40_), .B0(new_n39_), .Y(new_n42_));
  INV        g032(.A(i6), .Y(new_n43_));
  OAI210     g033(.A0(new_n23_), .A1(i0), .B0(new_n43_), .Y(new_n44_));
  OAI220     g034(.A0(new_n44_), .A1(new_n42_), .B0(new_n38_), .B1(new_n12_), .Y(new_n45_));
  AOI210     g035(.A0(new_n34_), .A1(new_n31_), .B0(new_n45_), .Y(new_n46_));
  AOI210     g036(.A0(new_n46_), .A1(new_n30_), .B0(new_n11_), .Y(new_n47_));
  NAi21      g037(.An(i4), .B(i1), .Y(new_n48_));
  NAi21      g038(.An(i1), .B(i6), .Y(new_n49_));
  AOI210     g039(.A0(new_n49_), .A1(new_n48_), .B0(i0), .Y(new_n50_));
  NO2        g040(.A(i6), .B(i1), .Y(new_n51_));
  NOi21      g041(.An(new_n51_), .B(new_n13_), .Y(new_n52_));
  OAI210     g042(.A0(new_n52_), .A1(new_n50_), .B0(i5), .Y(new_n53_));
  NAi21      g043(.An(i8), .B(i4), .Y(new_n54_));
  AOI210     g044(.A0(new_n54_), .A1(new_n33_), .B0(i1), .Y(new_n55_));
  OAI210     g045(.A0(new_n48_), .A1(new_n17_), .B0(new_n54_), .Y(new_n56_));
  AOI210     g046(.A0(new_n56_), .A1(new_n43_), .B0(new_n55_), .Y(new_n57_));
  AOI210     g047(.A0(new_n57_), .A1(new_n53_), .B0(i3), .Y(new_n58_));
  NO2        g048(.A(new_n13_), .B(i6), .Y(new_n59_));
  NO3        g049(.A(new_n17_), .B(i4), .C(i1), .Y(new_n60_));
  OAI210     g050(.A0(new_n60_), .A1(new_n59_), .B0(i3), .Y(new_n61_));
  OAI210     g051(.A0(new_n13_), .A1(i4), .B0(new_n20_), .Y(new_n62_));
  NO2        g052(.A(i7), .B(new_n11_), .Y(new_n63_));
  NA2        g053(.A(i7), .B(i6), .Y(new_n64_));
  OAI220     g054(.A0(new_n64_), .A1(i5), .B0(i6), .B1(new_n31_), .Y(new_n65_));
  AOI210     g055(.A0(new_n63_), .A1(new_n62_), .B0(new_n65_), .Y(new_n66_));
  AOI210     g056(.A0(new_n66_), .A1(new_n61_), .B0(i0), .Y(new_n67_));
  NO3        g057(.A(new_n14_), .B(i6), .C(new_n12_), .Y(new_n68_));
  NO3        g058(.A(new_n49_), .B(new_n13_), .C(i5), .Y(new_n69_));
  OA210      g059(.A0(new_n69_), .A1(new_n68_), .B0(new_n31_), .Y(new_n70_));
  INV        g060(.A(i5), .Y(new_n71_));
  NO3        g061(.A(i8), .B(i7), .C(i4), .Y(new_n72_));
  AOI210     g062(.A0(new_n51_), .A1(new_n71_), .B0(new_n72_), .Y(new_n73_));
  NA2        g063(.A(new_n17_), .B(i3), .Y(new_n74_));
  OAI220     g064(.A0(new_n74_), .A1(new_n49_), .B0(new_n73_), .B1(new_n39_), .Y(new_n75_));
  NO4        g065(.A(new_n75_), .B(new_n70_), .C(new_n67_), .D(new_n58_), .Y(new_n76_));
  NO2        g066(.A(new_n76_), .B(new_n40_), .Y(new_n77_));
  NO2        g067(.A(new_n17_), .B(i6), .Y(new_n78_));
  NA2        g068(.A(new_n78_), .B(new_n12_), .Y(new_n79_));
  AOI210     g069(.A0(new_n79_), .A1(new_n20_), .B0(new_n31_), .Y(new_n80_));
  NA2        g070(.A(i3), .B(new_n40_), .Y(new_n81_));
  NA2        g071(.A(new_n81_), .B(i4), .Y(new_n82_));
  NA2        g072(.A(new_n43_), .B(i4), .Y(new_n83_));
  NAi21      g073(.An(i3), .B(i6), .Y(new_n84_));
  AOI210     g074(.A0(new_n84_), .A1(new_n83_), .B0(new_n39_), .Y(new_n85_));
  AOI210     g075(.A0(new_n85_), .A1(new_n82_), .B0(new_n80_), .Y(new_n86_));
  AOI210     g076(.A0(i8), .A1(i6), .B0(i7), .Y(new_n87_));
  OAI220     g077(.A0(new_n87_), .A1(new_n71_), .B0(new_n64_), .B1(new_n12_), .Y(new_n88_));
  NO2        g078(.A(new_n71_), .B(i3), .Y(new_n89_));
  AOI220     g079(.A0(new_n89_), .A1(new_n78_), .B0(new_n88_), .B1(new_n31_), .Y(new_n90_));
  OAI220     g080(.A0(new_n90_), .A1(i2), .B0(new_n86_), .B1(i5), .Y(new_n91_));
  OAI220     g081(.A0(new_n13_), .A1(i2), .B0(i4), .B1(new_n12_), .Y(new_n92_));
  AOI210     g082(.A0(new_n92_), .A1(i0), .B0(new_n41_), .Y(new_n93_));
  NO2        g083(.A(new_n13_), .B(i2), .Y(new_n94_));
  NAi21      g084(.An(i5), .B(i3), .Y(new_n95_));
  NA2        g085(.A(new_n95_), .B(new_n84_), .Y(new_n96_));
  AOI220     g086(.A0(new_n96_), .A1(new_n94_), .B0(new_n89_), .B1(new_n31_), .Y(new_n97_));
  OAI210     g087(.A0(new_n93_), .A1(i6), .B0(new_n97_), .Y(new_n98_));
  NA2        g088(.A(i8), .B(i5), .Y(new_n99_));
  OAI220     g089(.A0(new_n81_), .A1(new_n99_), .B0(new_n20_), .B1(new_n31_), .Y(new_n100_));
  OAI220     g090(.A0(new_n54_), .A1(i2), .B0(new_n13_), .B1(i7), .Y(new_n101_));
  AO210      g091(.A0(new_n101_), .A1(i5), .B0(new_n21_), .Y(new_n102_));
  MUX2       g092(.S(new_n39_), .A(new_n102_), .B(new_n100_), .Y(new_n103_));
  AOI210     g093(.A0(new_n98_), .A1(i7), .B0(new_n103_), .Y(new_n104_));
  NAi21      g094(.An(i3), .B(i5), .Y(new_n105_));
  AOI210     g095(.A0(new_n95_), .A1(new_n105_), .B0(i2), .Y(new_n106_));
  NO3        g096(.A(i6), .B(i5), .C(new_n39_), .Y(new_n107_));
  OAI210     g097(.A0(new_n107_), .A1(new_n106_), .B0(i8), .Y(new_n108_));
  NO2        g098(.A(new_n84_), .B(i5), .Y(new_n109_));
  NO2        g099(.A(new_n109_), .B(new_n68_), .Y(new_n110_));
  AOI210     g100(.A0(new_n110_), .A1(new_n108_), .B0(new_n31_), .Y(new_n111_));
  NO2        g101(.A(i6), .B(new_n39_), .Y(new_n112_));
  AOI210     g102(.A0(new_n99_), .A1(i6), .B0(new_n112_), .Y(new_n113_));
  AOI210     g103(.A0(i5), .A1(new_n40_), .B0(new_n27_), .Y(new_n114_));
  NO3        g104(.A(new_n114_), .B(new_n113_), .C(new_n12_), .Y(new_n115_));
  OAI210     g105(.A0(new_n115_), .A1(new_n111_), .B0(new_n17_), .Y(new_n116_));
  OAI210     g106(.A0(new_n104_), .A1(i1), .B0(new_n116_), .Y(new_n117_));
  OR4        g107(.A(new_n117_), .B(new_n91_), .C(new_n77_), .D(new_n47_), .Y(zori0));
endmodule


