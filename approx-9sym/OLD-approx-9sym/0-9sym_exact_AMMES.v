// Benchmark "9sym_AMMES" written by ABC on Sat Jul 29 01:02:08 2023

module \9sym_AMMES  ( 
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
    new_n150_, new_n151_, new_n152_;
  INV        g000(.A(i5), .Y(new_n11_));
  NO2        g001(.A(new_n11_), .B(i3), .Y(new_n12_));
  INV        g002(.A(i8), .Y(new_n13_));
  NO2        g003(.A(new_n13_), .B(i7), .Y(new_n14_));
  OR2        g004(.A(new_n14_), .B(new_n12_), .Y(new_n15_));
  NOi21      g005(.An(new_n15_), .B(i0), .Y(new_n16_));
  INV        g006(.A(i7), .Y(new_n17_));
  INV        g007(.A(i3), .Y(new_n18_));
  INV        g008(.A(i6), .Y(new_n19_));
  NA2        g009(.A(new_n19_), .B(new_n18_), .Y(new_n20_));
  NO2        g010(.A(new_n20_), .B(new_n17_), .Y(new_n21_));
  OAI210     g011(.A0(new_n21_), .A1(new_n16_), .B0(i1), .Y(new_n22_));
  INV        g012(.A(i0), .Y(new_n23_));
  NO2        g013(.A(i7), .B(new_n18_), .Y(new_n24_));
  NA2        g014(.A(new_n24_), .B(new_n13_), .Y(new_n25_));
  NO2        g015(.A(new_n19_), .B(i5), .Y(new_n26_));
  NA2        g016(.A(new_n26_), .B(new_n18_), .Y(new_n27_));
  AOI210     g017(.A0(new_n27_), .A1(new_n25_), .B0(new_n23_), .Y(new_n28_));
  NA2        g018(.A(new_n26_), .B(i8), .Y(new_n29_));
  NA3        g019(.A(i7), .B(i3), .C(new_n23_), .Y(new_n30_));
  AOI210     g020(.A0(new_n30_), .A1(new_n29_), .B0(i1), .Y(new_n31_));
  NO4        g021(.A(new_n17_), .B(new_n19_), .C(i5), .D(i0), .Y(new_n32_));
  NAi21      g022(.An(i6), .B(i5), .Y(new_n33_));
  NA2        g023(.A(new_n11_), .B(i1), .Y(new_n34_));
  NA2        g024(.A(new_n13_), .B(i3), .Y(new_n35_));
  AOI210     g025(.A0(new_n34_), .A1(new_n33_), .B0(new_n35_), .Y(new_n36_));
  NO4        g026(.A(new_n36_), .B(new_n32_), .C(new_n31_), .D(new_n28_), .Y(new_n37_));
  AOI210     g027(.A0(new_n37_), .A1(new_n22_), .B0(i4), .Y(new_n38_));
  INV        g028(.A(i1), .Y(new_n39_));
  NO2        g029(.A(new_n13_), .B(i3), .Y(new_n40_));
  INV        g030(.A(i4), .Y(new_n41_));
  NO2        g031(.A(i6), .B(new_n41_), .Y(new_n42_));
  OAI210     g032(.A0(new_n42_), .A1(new_n40_), .B0(i0), .Y(new_n43_));
  NO2        g033(.A(i8), .B(new_n19_), .Y(new_n44_));
  NO2        g034(.A(new_n13_), .B(i0), .Y(new_n45_));
  OAI210     g035(.A0(new_n45_), .A1(new_n44_), .B0(i3), .Y(new_n46_));
  AOI210     g036(.A0(new_n46_), .A1(new_n43_), .B0(i7), .Y(new_n47_));
  NAi21      g037(.An(i5), .B(i4), .Y(new_n48_));
  AOI210     g038(.A0(new_n48_), .A1(new_n33_), .B0(i0), .Y(new_n49_));
  AOI210     g039(.A0(new_n40_), .A1(new_n11_), .B0(new_n49_), .Y(new_n50_));
  NAi21      g040(.An(i6), .B(i7), .Y(new_n51_));
  NAi21      g041(.An(i8), .B(i5), .Y(new_n52_));
  OAI220     g042(.A0(new_n52_), .A1(i3), .B0(new_n51_), .B1(i5), .Y(new_n53_));
  NAi21      g043(.An(i6), .B(i8), .Y(new_n54_));
  OAI210     g044(.A0(new_n19_), .A1(i0), .B0(new_n54_), .Y(new_n55_));
  AOI220     g045(.A0(new_n55_), .A1(new_n12_), .B0(new_n53_), .B1(i0), .Y(new_n56_));
  OAI210     g046(.A0(new_n50_), .A1(new_n17_), .B0(new_n56_), .Y(new_n57_));
  OAI210     g047(.A0(new_n57_), .A1(new_n47_), .B0(new_n39_), .Y(new_n58_));
  NO2        g048(.A(i8), .B(new_n18_), .Y(new_n59_));
  AOI220     g049(.A0(new_n45_), .A1(new_n17_), .B0(new_n59_), .B1(new_n19_), .Y(new_n60_));
  OAI220     g050(.A0(i7), .A1(new_n11_), .B0(i6), .B1(new_n39_), .Y(new_n61_));
  NO3        g051(.A(i8), .B(i7), .C(new_n19_), .Y(new_n62_));
  AOI210     g052(.A0(new_n61_), .A1(new_n23_), .B0(new_n62_), .Y(new_n63_));
  OAI220     g053(.A0(new_n63_), .A1(i3), .B0(new_n60_), .B1(i5), .Y(new_n64_));
  NA2        g054(.A(new_n13_), .B(i0), .Y(new_n65_));
  OR2        g055(.A(new_n65_), .B(new_n20_), .Y(new_n66_));
  NO2        g056(.A(new_n13_), .B(i6), .Y(new_n67_));
  NO2        g057(.A(i7), .B(i0), .Y(new_n68_));
  OAI210     g058(.A0(new_n67_), .A1(new_n44_), .B0(new_n68_), .Y(new_n69_));
  AOI210     g059(.A0(new_n69_), .A1(new_n66_), .B0(new_n39_), .Y(new_n70_));
  AOI210     g060(.A0(new_n64_), .A1(i4), .B0(new_n70_), .Y(new_n71_));
  NA2        g061(.A(new_n71_), .B(new_n58_), .Y(new_n72_));
  OAI210     g062(.A0(new_n72_), .A1(new_n38_), .B0(i2), .Y(new_n73_));
  AOI210     g063(.A0(new_n54_), .A1(new_n35_), .B0(i4), .Y(new_n74_));
  NO3        g064(.A(i8), .B(new_n41_), .C(i3), .Y(new_n75_));
  OAI210     g065(.A0(new_n75_), .A1(new_n74_), .B0(i5), .Y(new_n76_));
  NO2        g066(.A(new_n18_), .B(i2), .Y(new_n77_));
  NO2        g067(.A(i8), .B(new_n17_), .Y(new_n78_));
  NO2        g068(.A(new_n78_), .B(new_n14_), .Y(new_n79_));
  NOi21      g069(.An(new_n77_), .B(new_n79_), .Y(new_n80_));
  NA2        g070(.A(new_n11_), .B(i3), .Y(new_n81_));
  INV        g071(.A(i2), .Y(new_n82_));
  NO2        g072(.A(new_n17_), .B(i5), .Y(new_n83_));
  AOI210     g073(.A0(i8), .A1(new_n82_), .B0(new_n83_), .Y(new_n84_));
  OAI220     g074(.A0(new_n84_), .A1(i3), .B0(new_n81_), .B1(i6), .Y(new_n85_));
  AOI210     g075(.A0(new_n85_), .A1(i4), .B0(new_n80_), .Y(new_n86_));
  AOI210     g076(.A0(new_n86_), .A1(new_n76_), .B0(new_n39_), .Y(new_n87_));
  NO2        g077(.A(new_n11_), .B(i1), .Y(new_n88_));
  NOi21      g078(.An(new_n34_), .B(new_n88_), .Y(new_n89_));
  AOI220     g079(.A0(new_n77_), .A1(i8), .B0(new_n44_), .B1(i4), .Y(new_n90_));
  NA3        g080(.A(i6), .B(new_n41_), .C(i3), .Y(new_n91_));
  OAI210     g081(.A0(new_n33_), .A1(new_n41_), .B0(new_n91_), .Y(new_n92_));
  NO2        g082(.A(new_n35_), .B(new_n33_), .Y(new_n93_));
  AOI210     g083(.A0(new_n92_), .A1(new_n82_), .B0(new_n93_), .Y(new_n94_));
  OAI220     g084(.A0(new_n94_), .A1(new_n17_), .B0(new_n90_), .B1(new_n89_), .Y(new_n95_));
  OAI210     g085(.A0(new_n95_), .A1(new_n87_), .B0(new_n23_), .Y(new_n96_));
  NO2        g086(.A(i8), .B(new_n23_), .Y(new_n97_));
  OAI210     g087(.A0(new_n97_), .A1(new_n26_), .B0(i3), .Y(new_n98_));
  XO2        g088(.A(i7), .B(i6), .Y(new_n99_));
  NA2        g089(.A(new_n99_), .B(i5), .Y(new_n100_));
  AO210      g090(.A0(new_n100_), .A1(new_n98_), .B0(i4), .Y(new_n101_));
  NO2        g091(.A(i7), .B(i6), .Y(new_n102_));
  NA2        g092(.A(new_n83_), .B(i8), .Y(new_n103_));
  OAI210     g093(.A0(new_n102_), .A1(new_n65_), .B0(new_n103_), .Y(new_n104_));
  AOI220     g094(.A0(new_n104_), .A1(new_n18_), .B0(new_n97_), .B1(new_n42_), .Y(new_n105_));
  AOI210     g095(.A0(new_n105_), .A1(new_n101_), .B0(new_n39_), .Y(new_n106_));
  AOI210     g096(.A0(i7), .A1(new_n39_), .B0(new_n40_), .Y(new_n107_));
  OAI210     g097(.A0(new_n107_), .A1(i4), .B0(new_n25_), .Y(new_n108_));
  NO2        g098(.A(i8), .B(new_n41_), .Y(new_n109_));
  AOI220     g099(.A0(new_n109_), .A1(new_n39_), .B0(new_n24_), .B1(new_n19_), .Y(new_n110_));
  OAI210     g100(.A0(i7), .A1(new_n41_), .B0(new_n51_), .Y(new_n111_));
  NA2        g101(.A(new_n111_), .B(new_n40_), .Y(new_n112_));
  OAI210     g102(.A0(new_n110_), .A1(new_n23_), .B0(new_n112_), .Y(new_n113_));
  AOI210     g103(.A0(new_n108_), .A1(i6), .B0(new_n113_), .Y(new_n114_));
  NO3        g104(.A(i6), .B(i5), .C(new_n18_), .Y(new_n115_));
  NO3        g105(.A(i8), .B(new_n19_), .C(i1), .Y(new_n116_));
  OAI210     g106(.A0(new_n116_), .A1(new_n115_), .B0(i4), .Y(new_n117_));
  NA3        g107(.A(new_n99_), .B(i8), .C(new_n39_), .Y(new_n118_));
  AOI210     g108(.A0(new_n118_), .A1(new_n117_), .B0(new_n23_), .Y(new_n119_));
  NO2        g109(.A(i5), .B(new_n41_), .Y(new_n120_));
  NA2        g110(.A(new_n120_), .B(new_n59_), .Y(new_n121_));
  NA2        g111(.A(i5), .B(i3), .Y(new_n122_));
  NA4        g112(.A(new_n122_), .B(new_n20_), .C(i8), .D(new_n39_), .Y(new_n123_));
  AOI210     g113(.A0(new_n123_), .A1(new_n121_), .B0(new_n17_), .Y(new_n124_));
  NO4        g114(.A(new_n81_), .B(new_n13_), .C(i7), .D(new_n41_), .Y(new_n125_));
  NO3        g115(.A(new_n125_), .B(new_n124_), .C(new_n119_), .Y(new_n126_));
  OAI210     g116(.A0(new_n114_), .A1(new_n11_), .B0(new_n126_), .Y(new_n127_));
  OAI210     g117(.A0(new_n127_), .A1(new_n106_), .B0(new_n82_), .Y(new_n128_));
  AOI210     g118(.A0(i7), .A1(new_n18_), .B0(new_n44_), .Y(new_n129_));
  OAI220     g119(.A0(new_n129_), .A1(new_n11_), .B0(new_n51_), .B1(new_n18_), .Y(new_n130_));
  OAI210     g120(.A0(new_n42_), .A1(new_n26_), .B0(new_n78_), .Y(new_n131_));
  NO2        g121(.A(new_n13_), .B(i4), .Y(new_n132_));
  AOI220     g122(.A0(new_n132_), .A1(i5), .B0(new_n26_), .B1(i3), .Y(new_n133_));
  OAI210     g123(.A0(new_n133_), .A1(i7), .B0(new_n131_), .Y(new_n134_));
  AOI210     g124(.A0(new_n130_), .A1(new_n41_), .B0(new_n134_), .Y(new_n135_));
  OAI210     g125(.A0(i4), .A1(new_n18_), .B0(new_n48_), .Y(new_n136_));
  NA2        g126(.A(new_n54_), .B(new_n52_), .Y(new_n137_));
  NO2        g127(.A(i3), .B(new_n39_), .Y(new_n138_));
  AOI220     g128(.A0(new_n138_), .A1(new_n137_), .B0(new_n136_), .B1(new_n67_), .Y(new_n139_));
  OAI220     g129(.A0(new_n139_), .A1(i7), .B0(new_n135_), .B1(i1), .Y(new_n140_));
  NO2        g130(.A(new_n132_), .B(new_n109_), .Y(new_n141_));
  NA2        g131(.A(new_n24_), .B(new_n11_), .Y(new_n142_));
  NO2        g132(.A(new_n142_), .B(new_n141_), .Y(new_n143_));
  AOI210     g133(.A0(new_n41_), .A1(i1), .B0(new_n120_), .Y(new_n144_));
  NO3        g134(.A(new_n144_), .B(new_n79_), .C(i3), .Y(new_n145_));
  OAI210     g135(.A0(new_n145_), .A1(new_n143_), .B0(i6), .Y(new_n146_));
  NO3        g136(.A(new_n103_), .B(i4), .C(new_n39_), .Y(new_n147_));
  AOI210     g137(.A0(new_n17_), .A1(i5), .B0(new_n40_), .Y(new_n148_));
  NO3        g138(.A(new_n148_), .B(new_n15_), .C(new_n41_), .Y(new_n149_));
  OAI210     g139(.A0(new_n149_), .A1(new_n147_), .B0(new_n19_), .Y(new_n150_));
  NA2        g140(.A(new_n150_), .B(new_n146_), .Y(new_n151_));
  AOI210     g141(.A0(new_n140_), .A1(i0), .B0(new_n151_), .Y(new_n152_));
  NA4        g142(.A(new_n152_), .B(new_n128_), .C(new_n96_), .D(new_n73_), .Y(zori0));
endmodule


