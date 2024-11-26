// Benchmark "9sym/9sym_1_1_0_esp" written by ABC on Wed Mar  8 02:18:24 2023

module \9sym/9sym_1_1_0_esp  ( 
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
    new_n144_, new_n145_, new_n146_, new_n147_, new_n148_, new_n149_,
    new_n150_, new_n151_;
  INV        g000(.A(i1), .Y(new_n11_));
  INV        g001(.A(i4), .Y(new_n12_));
  INV        g002(.A(i5), .Y(new_n13_));
  NAi21      g003(.An(i7), .B(i8), .Y(new_n14_));
  OAI210     g004(.A0(new_n13_), .A1(i3), .B0(new_n14_), .Y(new_n15_));
  NA2        g005(.A(new_n15_), .B(new_n12_), .Y(new_n16_));
  INV        g006(.A(i6), .Y(new_n17_));
  INV        g007(.A(i8), .Y(new_n18_));
  NO2        g008(.A(new_n18_), .B(i7), .Y(new_n19_));
  NA2        g009(.A(new_n19_), .B(new_n17_), .Y(new_n20_));
  AOI210     g010(.A0(new_n20_), .A1(new_n16_), .B0(new_n11_), .Y(new_n21_));
  NO3        g011(.A(i8), .B(i7), .C(new_n11_), .Y(new_n22_));
  INV        g012(.A(i7), .Y(new_n23_));
  NO2        g013(.A(new_n23_), .B(i4), .Y(new_n24_));
  AOI210     g014(.A0(new_n24_), .A1(new_n13_), .B0(new_n22_), .Y(new_n25_));
  INV        g015(.A(i3), .Y(new_n26_));
  NAi21      g016(.An(i4), .B(i7), .Y(new_n27_));
  AOI210     g017(.A0(new_n27_), .A1(new_n14_), .B0(new_n26_), .Y(new_n28_));
  NAi21      g018(.An(i6), .B(i7), .Y(new_n29_));
  OAI210     g019(.A0(new_n17_), .A1(i3), .B0(new_n29_), .Y(new_n30_));
  AOI210     g020(.A0(new_n30_), .A1(i5), .B0(new_n28_), .Y(new_n31_));
  OAI220     g021(.A0(new_n31_), .A1(i1), .B0(new_n25_), .B1(new_n17_), .Y(new_n32_));
  OAI210     g022(.A0(new_n32_), .A1(new_n21_), .B0(i2), .Y(new_n33_));
  NAi21      g023(.An(i6), .B(i3), .Y(new_n34_));
  NAi21      g024(.An(i3), .B(i7), .Y(new_n35_));
  AOI210     g025(.A0(new_n35_), .A1(new_n34_), .B0(new_n11_), .Y(new_n36_));
  AN2        g026(.A(i8), .B(i2), .Y(new_n37_));
  MUX2       g027(.S(new_n37_), .A(i6), .B(new_n23_), .Y(new_n38_));
  OAI210     g028(.A0(new_n38_), .A1(new_n36_), .B0(new_n13_), .Y(new_n39_));
  INV        g029(.A(i2), .Y(new_n40_));
  NAi21      g030(.An(i6), .B(i1), .Y(new_n41_));
  NAi21      g031(.An(i7), .B(i5), .Y(new_n42_));
  AOI210     g032(.A0(new_n42_), .A1(new_n41_), .B0(new_n40_), .Y(new_n43_));
  NO2        g033(.A(i8), .B(i5), .Y(new_n44_));
  NO3        g034(.A(new_n44_), .B(new_n37_), .C(new_n11_), .Y(new_n45_));
  OAI210     g035(.A0(new_n45_), .A1(new_n43_), .B0(new_n26_), .Y(new_n46_));
  NAi21      g036(.An(i2), .B(i5), .Y(new_n47_));
  NO2        g037(.A(new_n47_), .B(new_n29_), .Y(new_n48_));
  NAi21      g038(.An(i5), .B(i7), .Y(new_n49_));
  OAI220     g039(.A0(new_n49_), .A1(new_n40_), .B0(i8), .B1(new_n17_), .Y(new_n50_));
  AOI210     g040(.A0(new_n50_), .A1(new_n11_), .B0(new_n48_), .Y(new_n51_));
  NA3        g041(.A(new_n51_), .B(new_n46_), .C(new_n39_), .Y(new_n52_));
  NAi21      g042(.An(i8), .B(i3), .Y(new_n53_));
  OAI210     g043(.A0(new_n18_), .A1(i6), .B0(new_n53_), .Y(new_n54_));
  NO2        g044(.A(i4), .B(new_n11_), .Y(new_n55_));
  NAi21      g045(.An(i2), .B(i8), .Y(new_n56_));
  NAi21      g046(.An(i8), .B(i7), .Y(new_n57_));
  OAI220     g047(.A0(new_n57_), .A1(i6), .B0(new_n56_), .B1(i1), .Y(new_n58_));
  AOI220     g048(.A0(new_n58_), .A1(i3), .B0(new_n55_), .B1(new_n54_), .Y(new_n59_));
  AN2        g049(.A(new_n57_), .B(new_n14_), .Y(new_n60_));
  OA220      g050(.A0(new_n60_), .A1(new_n11_), .B0(new_n27_), .B1(new_n17_), .Y(new_n61_));
  NA2        g051(.A(i3), .B(new_n40_), .Y(new_n62_));
  OAI220     g052(.A0(new_n62_), .A1(new_n61_), .B0(new_n59_), .B1(new_n13_), .Y(new_n63_));
  AOI210     g053(.A0(new_n52_), .A1(i4), .B0(new_n63_), .Y(new_n64_));
  AOI210     g054(.A0(new_n64_), .A1(new_n33_), .B0(i0), .Y(new_n65_));
  NO2        g055(.A(i7), .B(new_n13_), .Y(new_n66_));
  NO2        g056(.A(i5), .B(new_n12_), .Y(new_n67_));
  OAI210     g057(.A0(new_n67_), .A1(new_n66_), .B0(new_n40_), .Y(new_n68_));
  NO2        g058(.A(new_n23_), .B(i1), .Y(new_n69_));
  OAI210     g059(.A0(new_n69_), .A1(new_n19_), .B0(new_n12_), .Y(new_n70_));
  AOI210     g060(.A0(new_n70_), .A1(new_n68_), .B0(new_n26_), .Y(new_n71_));
  NO3        g061(.A(i8), .B(new_n12_), .C(i2), .Y(new_n72_));
  NO2        g062(.A(new_n14_), .B(i3), .Y(new_n73_));
  OAI210     g063(.A0(new_n73_), .A1(new_n72_), .B0(i1), .Y(new_n74_));
  NA2        g064(.A(new_n67_), .B(new_n19_), .Y(new_n75_));
  OAI210     g065(.A0(i8), .A1(new_n12_), .B0(new_n56_), .Y(new_n76_));
  NA2        g066(.A(new_n76_), .B(new_n69_), .Y(new_n77_));
  NA3        g067(.A(new_n77_), .B(new_n75_), .C(new_n74_), .Y(new_n78_));
  OAI210     g068(.A0(new_n78_), .A1(new_n71_), .B0(i0), .Y(new_n79_));
  INV        g069(.A(i0), .Y(new_n80_));
  OAI210     g070(.A0(i8), .A1(new_n80_), .B0(new_n27_), .Y(new_n81_));
  NO2        g071(.A(new_n18_), .B(i1), .Y(new_n82_));
  AOI220     g072(.A0(new_n82_), .A1(i5), .B0(new_n81_), .B1(i1), .Y(new_n83_));
  OAI210     g073(.A0(i7), .A1(new_n12_), .B0(new_n49_), .Y(new_n84_));
  NO2        g074(.A(i1), .B(new_n80_), .Y(new_n85_));
  NAi21      g075(.An(i5), .B(i4), .Y(new_n86_));
  NAi21      g076(.An(i4), .B(i5), .Y(new_n87_));
  AOI210     g077(.A0(new_n87_), .A1(new_n86_), .B0(new_n53_), .Y(new_n88_));
  AOI210     g078(.A0(new_n85_), .A1(new_n84_), .B0(new_n88_), .Y(new_n89_));
  OAI210     g079(.A0(new_n83_), .A1(i3), .B0(new_n89_), .Y(new_n90_));
  OAI210     g080(.A0(new_n18_), .A1(i5), .B0(new_n47_), .Y(new_n91_));
  NAi21      g081(.An(i3), .B(i8), .Y(new_n92_));
  AOI210     g082(.A0(new_n86_), .A1(new_n47_), .B0(new_n92_), .Y(new_n93_));
  AOI210     g083(.A0(new_n91_), .A1(new_n55_), .B0(new_n93_), .Y(new_n94_));
  NO2        g084(.A(i8), .B(new_n26_), .Y(new_n95_));
  NA3        g085(.A(new_n95_), .B(new_n66_), .C(i4), .Y(new_n96_));
  OAI210     g086(.A0(new_n94_), .A1(new_n23_), .B0(new_n96_), .Y(new_n97_));
  AOI210     g087(.A0(new_n90_), .A1(i2), .B0(new_n97_), .Y(new_n98_));
  AOI210     g088(.A0(new_n98_), .A1(new_n79_), .B0(i6), .Y(new_n99_));
  NA2        g089(.A(new_n24_), .B(i6), .Y(new_n100_));
  NA3        g090(.A(new_n18_), .B(i4), .C(i0), .Y(new_n101_));
  AOI210     g091(.A0(new_n101_), .A1(new_n100_), .B0(i1), .Y(new_n102_));
  NO2        g092(.A(new_n17_), .B(i4), .Y(new_n103_));
  AOI210     g093(.A0(new_n23_), .A1(i4), .B0(new_n103_), .Y(new_n104_));
  NO2        g094(.A(i7), .B(new_n17_), .Y(new_n105_));
  OAI210     g095(.A0(new_n55_), .A1(new_n95_), .B0(new_n105_), .Y(new_n106_));
  OAI210     g096(.A0(new_n104_), .A1(new_n92_), .B0(new_n106_), .Y(new_n107_));
  OAI210     g097(.A0(new_n107_), .A1(new_n102_), .B0(i5), .Y(new_n108_));
  NA2        g098(.A(new_n18_), .B(i0), .Y(new_n109_));
  NAi21      g099(.An(i4), .B(i3), .Y(new_n110_));
  AN2        g100(.A(new_n110_), .B(new_n35_), .Y(new_n111_));
  NO2        g101(.A(new_n23_), .B(i3), .Y(new_n112_));
  AOI220     g102(.A0(new_n103_), .A1(i3), .B0(new_n112_), .B1(i8), .Y(new_n113_));
  OAI220     g103(.A0(new_n113_), .A1(i5), .B0(new_n111_), .B1(new_n109_), .Y(new_n114_));
  AOI210     g104(.A0(new_n23_), .A1(i0), .B0(new_n112_), .Y(new_n115_));
  OAI220     g105(.A0(new_n115_), .A1(new_n17_), .B0(new_n49_), .B1(new_n26_), .Y(new_n116_));
  AOI220     g106(.A0(new_n116_), .A1(new_n82_), .B0(new_n114_), .B1(i1), .Y(new_n117_));
  AOI210     g107(.A0(new_n117_), .A1(new_n108_), .B0(i2), .Y(new_n118_));
  XN2        g108(.A(i7), .B(i1), .Y(new_n119_));
  OAI220     g109(.A0(new_n119_), .A1(new_n80_), .B0(new_n12_), .B1(i3), .Y(new_n120_));
  NO2        g110(.A(new_n26_), .B(new_n40_), .Y(new_n121_));
  AOI220     g111(.A0(new_n121_), .A1(new_n55_), .B0(new_n120_), .B1(i6), .Y(new_n122_));
  NO2        g112(.A(new_n122_), .B(i8), .Y(new_n123_));
  NO4        g113(.A(new_n35_), .B(new_n18_), .C(new_n40_), .D(i1), .Y(new_n124_));
  NO3        g114(.A(i7), .B(new_n26_), .C(i1), .Y(new_n125_));
  NO2        g115(.A(i4), .B(i3), .Y(new_n126_));
  NOi21      g116(.An(new_n126_), .B(new_n40_), .Y(new_n127_));
  OAI210     g117(.A0(new_n127_), .A1(new_n125_), .B0(i0), .Y(new_n128_));
  NO2        g118(.A(new_n40_), .B(i1), .Y(new_n129_));
  NO2        g119(.A(new_n18_), .B(i4), .Y(new_n130_));
  OAI210     g120(.A0(new_n18_), .A1(i4), .B0(i3), .Y(new_n131_));
  NO2        g121(.A(new_n126_), .B(i7), .Y(new_n132_));
  AOI220     g122(.A0(new_n132_), .A1(new_n131_), .B0(new_n130_), .B1(new_n129_), .Y(new_n133_));
  AOI210     g123(.A0(new_n133_), .A1(new_n128_), .B0(new_n17_), .Y(new_n134_));
  NO3        g124(.A(new_n134_), .B(new_n124_), .C(new_n123_), .Y(new_n135_));
  NAi21      g125(.An(i1), .B(i8), .Y(new_n136_));
  OAI220     g126(.A0(new_n110_), .A1(i8), .B0(new_n136_), .B1(i3), .Y(new_n137_));
  NAi21      g127(.An(i3), .B(i1), .Y(new_n138_));
  OAI220     g128(.A0(new_n138_), .A1(i8), .B0(new_n136_), .B1(i4), .Y(new_n139_));
  AOI220     g129(.A0(new_n139_), .A1(i5), .B0(new_n137_), .B1(i2), .Y(new_n140_));
  NO2        g130(.A(i8), .B(new_n17_), .Y(new_n141_));
  NA3        g131(.A(new_n129_), .B(new_n141_), .C(i3), .Y(new_n142_));
  OAI210     g132(.A0(new_n140_), .A1(new_n80_), .B0(new_n142_), .Y(new_n143_));
  NA2        g133(.A(i6), .B(new_n12_), .Y(new_n144_));
  OA220      g134(.A0(new_n138_), .A1(new_n144_), .B0(new_n86_), .B1(new_n62_), .Y(new_n145_));
  OAI210     g135(.A0(i8), .A1(new_n40_), .B0(new_n27_), .Y(new_n146_));
  AOI220     g136(.A0(new_n146_), .A1(new_n26_), .B0(new_n141_), .B1(new_n12_), .Y(new_n147_));
  NA2        g137(.A(new_n85_), .B(i5), .Y(new_n148_));
  OAI220     g138(.A0(new_n148_), .A1(new_n147_), .B0(new_n145_), .B1(new_n60_), .Y(new_n149_));
  AOI210     g139(.A0(new_n143_), .A1(new_n23_), .B0(new_n149_), .Y(new_n150_));
  OAI210     g140(.A0(new_n135_), .A1(i5), .B0(new_n150_), .Y(new_n151_));
  OR4        g141(.A(new_n151_), .B(new_n118_), .C(new_n99_), .D(new_n65_), .Y(zori0));
endmodule


