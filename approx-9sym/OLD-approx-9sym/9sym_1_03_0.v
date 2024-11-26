// Benchmark "9sym/9sym_1_3_0_esp" written by ABC on Wed Mar  8 02:24:08 2023

module \9sym/9sym_1_3_0_esp  ( 
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
    new_n126_, new_n127_, new_n128_, new_n129_, new_n130_, new_n131_,
    new_n132_, new_n133_, new_n134_, new_n135_, new_n136_, new_n137_,
    new_n138_, new_n139_, new_n140_, new_n141_, new_n142_, new_n143_,
    new_n144_, new_n145_, new_n146_, new_n147_, new_n148_;
  INV        g000(.A(i2), .Y(new_n11_));
  INV        g001(.A(i0), .Y(new_n12_));
  INV        g002(.A(i3), .Y(new_n13_));
  INV        g003(.A(i1), .Y(new_n14_));
  NAi21      g004(.An(i6), .B(i4), .Y(new_n15_));
  NAi21      g005(.An(i4), .B(i5), .Y(new_n16_));
  AOI210     g006(.A0(new_n16_), .A1(new_n15_), .B0(new_n14_), .Y(new_n17_));
  INV        g007(.A(i6), .Y(new_n18_));
  NA2        g008(.A(i5), .B(new_n14_), .Y(new_n19_));
  NO2        g009(.A(new_n19_), .B(new_n18_), .Y(new_n20_));
  OA210      g010(.A0(new_n20_), .A1(new_n17_), .B0(new_n13_), .Y(new_n21_));
  NAi21      g011(.An(i1), .B(i7), .Y(new_n22_));
  NAi21      g012(.An(i4), .B(i3), .Y(new_n23_));
  NA2        g013(.A(new_n18_), .B(i5), .Y(new_n24_));
  AOI210     g014(.A0(new_n24_), .A1(new_n23_), .B0(new_n22_), .Y(new_n25_));
  OAI210     g015(.A0(new_n25_), .A1(new_n21_), .B0(new_n12_), .Y(new_n26_));
  INV        g016(.A(i5), .Y(new_n27_));
  NAi21      g017(.An(i4), .B(i6), .Y(new_n28_));
  NO2        g018(.A(new_n28_), .B(i3), .Y(new_n29_));
  INV        g019(.A(i7), .Y(new_n30_));
  NO2        g020(.A(i6), .B(i1), .Y(new_n31_));
  NOi21      g021(.An(new_n31_), .B(new_n30_), .Y(new_n32_));
  OAI210     g022(.A0(new_n32_), .A1(new_n29_), .B0(i0), .Y(new_n33_));
  INV        g023(.A(i4), .Y(new_n34_));
  INV        g024(.A(i8), .Y(new_n35_));
  OAI220     g025(.A0(new_n35_), .A1(new_n34_), .B0(new_n30_), .B1(new_n18_), .Y(new_n36_));
  NA2        g026(.A(new_n36_), .B(new_n12_), .Y(new_n37_));
  NA2        g027(.A(i7), .B(new_n13_), .Y(new_n38_));
  NA2        g028(.A(i8), .B(new_n14_), .Y(new_n39_));
  AO210      g029(.A0(new_n38_), .A1(new_n28_), .B0(new_n39_), .Y(new_n40_));
  NA3        g030(.A(new_n40_), .B(new_n37_), .C(new_n33_), .Y(new_n41_));
  NAi31      g031(.An(i1), .B(i8), .C(i5), .Y(new_n42_));
  NAi21      g032(.An(i4), .B(i1), .Y(new_n43_));
  OAI210     g033(.A0(new_n43_), .A1(new_n30_), .B0(new_n42_), .Y(new_n44_));
  NO2        g034(.A(i6), .B(i3), .Y(new_n45_));
  AOI220     g035(.A0(new_n45_), .A1(new_n44_), .B0(new_n41_), .B1(new_n27_), .Y(new_n46_));
  AOI210     g036(.A0(new_n46_), .A1(new_n26_), .B0(new_n11_), .Y(new_n47_));
  NO2        g037(.A(new_n22_), .B(new_n35_), .Y(new_n48_));
  NO2        g038(.A(i5), .B(new_n34_), .Y(new_n49_));
  AOI210     g039(.A0(new_n49_), .A1(i3), .B0(new_n48_), .Y(new_n50_));
  OAI210     g040(.A0(new_n35_), .A1(i3), .B0(new_n43_), .Y(new_n51_));
  AOI220     g041(.A0(new_n51_), .A1(i5), .B0(i4), .B1(new_n12_), .Y(new_n52_));
  OAI220     g042(.A0(new_n52_), .A1(new_n30_), .B0(new_n50_), .B1(new_n12_), .Y(new_n53_));
  NAi31      g043(.An(i4), .B(i7), .C(i6), .Y(new_n54_));
  AOI210     g044(.A0(new_n54_), .A1(new_n42_), .B0(i0), .Y(new_n55_));
  NAi31      g045(.An(i1), .B(i8), .C(i7), .Y(new_n56_));
  NAi31      g046(.An(i4), .B(i6), .C(i1), .Y(new_n57_));
  AOI210     g047(.A0(new_n57_), .A1(new_n56_), .B0(i5), .Y(new_n58_));
  OAI210     g048(.A0(new_n58_), .A1(new_n55_), .B0(i3), .Y(new_n59_));
  OR2        g049(.A(new_n54_), .B(new_n19_), .Y(new_n60_));
  NO2        g050(.A(new_n35_), .B(i3), .Y(new_n61_));
  NAi21      g051(.An(i0), .B(i4), .Y(new_n62_));
  NAi21      g052(.An(i5), .B(i7), .Y(new_n63_));
  AOI210     g053(.A0(new_n63_), .A1(new_n62_), .B0(new_n14_), .Y(new_n64_));
  AOI210     g054(.A0(new_n22_), .A1(new_n16_), .B0(new_n18_), .Y(new_n65_));
  OAI210     g055(.A0(new_n65_), .A1(new_n64_), .B0(new_n61_), .Y(new_n66_));
  NA3        g056(.A(new_n66_), .B(new_n60_), .C(new_n59_), .Y(new_n67_));
  AOI210     g057(.A0(new_n53_), .A1(new_n18_), .B0(new_n67_), .Y(new_n68_));
  NAi21      g058(.An(i1), .B(i0), .Y(new_n69_));
  NAi21      g059(.An(i3), .B(i5), .Y(new_n70_));
  NAi21      g060(.An(i6), .B(i3), .Y(new_n71_));
  AOI210     g061(.A0(new_n71_), .A1(new_n70_), .B0(new_n69_), .Y(new_n72_));
  NAi21      g062(.An(i6), .B(i1), .Y(new_n73_));
  NO3        g063(.A(new_n73_), .B(new_n35_), .C(i5), .Y(new_n74_));
  OAI210     g064(.A0(new_n74_), .A1(new_n72_), .B0(i7), .Y(new_n75_));
  OR4        g065(.A(new_n24_), .B(new_n35_), .C(new_n14_), .D(i0), .Y(new_n76_));
  AOI210     g066(.A0(new_n76_), .A1(new_n75_), .B0(i4), .Y(new_n77_));
  NAi31      g067(.An(i0), .B(i3), .C(i1), .Y(new_n78_));
  OAI210     g068(.A0(new_n38_), .A1(i6), .B0(new_n78_), .Y(new_n79_));
  AOI210     g069(.A0(new_n79_), .A1(new_n49_), .B0(new_n77_), .Y(new_n80_));
  OAI210     g070(.A0(new_n68_), .A1(i2), .B0(new_n80_), .Y(new_n81_));
  NO2        g071(.A(new_n69_), .B(i3), .Y(new_n82_));
  NAi21      g072(.An(i1), .B(i3), .Y(new_n83_));
  NA3        g073(.A(new_n83_), .B(new_n73_), .C(new_n43_), .Y(new_n84_));
  AOI210     g074(.A0(new_n84_), .A1(new_n12_), .B0(new_n82_), .Y(new_n85_));
  NOi31      g075(.An(i3), .B(i8), .C(i4), .Y(new_n86_));
  NOi31      g076(.An(i4), .B(i6), .C(i1), .Y(new_n87_));
  OA210      g077(.A0(new_n87_), .A1(new_n86_), .B0(i0), .Y(new_n88_));
  NAi21      g078(.An(i8), .B(i3), .Y(new_n89_));
  NO3        g079(.A(new_n89_), .B(new_n18_), .C(i1), .Y(new_n90_));
  NAi31      g080(.An(i8), .B(i6), .C(i1), .Y(new_n91_));
  NAi31      g081(.An(i3), .B(i5), .C(i4), .Y(new_n92_));
  AOI210     g082(.A0(new_n92_), .A1(new_n91_), .B0(i0), .Y(new_n93_));
  NO3        g083(.A(new_n93_), .B(new_n90_), .C(new_n88_), .Y(new_n94_));
  OAI210     g084(.A0(new_n85_), .A1(new_n35_), .B0(new_n94_), .Y(new_n95_));
  NA2        g085(.A(new_n95_), .B(i2), .Y(new_n96_));
  NAi21      g086(.An(i2), .B(i4), .Y(new_n97_));
  AOI210     g087(.A0(new_n97_), .A1(new_n28_), .B0(new_n13_), .Y(new_n98_));
  OAI210     g088(.A0(new_n15_), .A1(new_n12_), .B0(new_n27_), .Y(new_n99_));
  NO2        g089(.A(new_n69_), .B(i4), .Y(new_n100_));
  OAI210     g090(.A0(new_n97_), .A1(i3), .B0(i5), .Y(new_n101_));
  OAI220     g091(.A0(new_n101_), .A1(new_n100_), .B0(new_n99_), .B1(new_n98_), .Y(new_n102_));
  NAi31      g092(.An(i1), .B(i6), .C(i0), .Y(new_n103_));
  AOI210     g093(.A0(new_n103_), .A1(new_n78_), .B0(i2), .Y(new_n104_));
  NAi21      g094(.An(i3), .B(i1), .Y(new_n105_));
  NAi21      g095(.An(i6), .B(i0), .Y(new_n106_));
  AOI210     g096(.A0(new_n105_), .A1(new_n23_), .B0(new_n106_), .Y(new_n107_));
  NO2        g097(.A(new_n57_), .B(i3), .Y(new_n108_));
  NO3        g098(.A(new_n108_), .B(new_n107_), .C(new_n104_), .Y(new_n109_));
  AOI210     g099(.A0(new_n109_), .A1(new_n102_), .B0(new_n35_), .Y(new_n110_));
  NO2        g100(.A(new_n71_), .B(i2), .Y(new_n111_));
  NO2        g101(.A(new_n105_), .B(i8), .Y(new_n112_));
  OAI210     g102(.A0(new_n112_), .A1(new_n111_), .B0(i0), .Y(new_n113_));
  NO2        g103(.A(new_n89_), .B(new_n15_), .Y(new_n114_));
  NA2        g104(.A(new_n89_), .B(new_n43_), .Y(new_n115_));
  NO2        g105(.A(new_n18_), .B(i2), .Y(new_n116_));
  AOI210     g106(.A0(new_n116_), .A1(new_n115_), .B0(new_n114_), .Y(new_n117_));
  AOI210     g107(.A0(new_n117_), .A1(new_n113_), .B0(new_n27_), .Y(new_n118_));
  NA2        g108(.A(i6), .B(new_n27_), .Y(new_n119_));
  NA2        g109(.A(i8), .B(i3), .Y(new_n120_));
  NO2        g110(.A(new_n83_), .B(new_n12_), .Y(new_n121_));
  AOI210     g111(.A0(new_n120_), .A1(i4), .B0(new_n121_), .Y(new_n122_));
  NO2        g112(.A(new_n122_), .B(new_n119_), .Y(new_n123_));
  NO3        g113(.A(new_n123_), .B(new_n118_), .C(new_n110_), .Y(new_n124_));
  AOI210     g114(.A0(new_n124_), .A1(new_n96_), .B0(i7), .Y(new_n125_));
  AOI210     g115(.A0(new_n119_), .A1(new_n38_), .B0(i2), .Y(new_n126_));
  NO3        g116(.A(i6), .B(i3), .C(new_n11_), .Y(new_n127_));
  OAI210     g117(.A0(new_n127_), .A1(new_n126_), .B0(i0), .Y(new_n128_));
  OA210      g118(.A0(new_n30_), .A1(i2), .B0(new_n16_), .Y(new_n129_));
  NOi21      g119(.An(i0), .B(i2), .Y(new_n130_));
  AOI210     g120(.A0(new_n27_), .A1(i2), .B0(new_n130_), .Y(new_n131_));
  OAI220     g121(.A0(new_n131_), .A1(i4), .B0(new_n129_), .B1(i0), .Y(new_n132_));
  AOI220     g122(.A0(new_n132_), .A1(i3), .B0(new_n29_), .B1(i7), .Y(new_n133_));
  AOI210     g123(.A0(new_n133_), .A1(new_n128_), .B0(new_n14_), .Y(new_n134_));
  OR2        g124(.A(new_n71_), .B(new_n11_), .Y(new_n135_));
  AOI210     g125(.A0(new_n135_), .A1(new_n38_), .B0(i5), .Y(new_n136_));
  NOi21      g126(.An(new_n70_), .B(new_n14_), .Y(new_n137_));
  NO3        g127(.A(new_n137_), .B(new_n31_), .C(i0), .Y(new_n138_));
  NA2        g128(.A(new_n11_), .B(i0), .Y(new_n139_));
  AOI210     g129(.A0(new_n73_), .A1(new_n19_), .B0(new_n139_), .Y(new_n140_));
  NO4        g130(.A(new_n140_), .B(new_n138_), .C(new_n136_), .D(new_n32_), .Y(new_n141_));
  NO2        g131(.A(new_n103_), .B(new_n63_), .Y(new_n142_));
  OA210      g132(.A0(i3), .A1(new_n11_), .B0(new_n28_), .Y(new_n143_));
  AOI220     g133(.A0(i7), .A1(new_n12_), .B0(new_n34_), .B1(i2), .Y(new_n144_));
  OAI220     g134(.A0(new_n144_), .A1(new_n71_), .B0(new_n143_), .B1(new_n69_), .Y(new_n145_));
  AOI210     g135(.A0(new_n145_), .A1(i5), .B0(new_n142_), .Y(new_n146_));
  OAI210     g136(.A0(new_n141_), .A1(new_n34_), .B0(new_n146_), .Y(new_n147_));
  OA210      g137(.A0(new_n147_), .A1(new_n134_), .B0(new_n35_), .Y(new_n148_));
  OR4        g138(.A(new_n148_), .B(new_n125_), .C(new_n81_), .D(new_n47_), .Y(zori0));
endmodule


