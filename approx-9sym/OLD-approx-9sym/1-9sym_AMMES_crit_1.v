// Benchmark "9Sym_AMMES_crit_1" written by ABC on Sat Jul 29 01:53:41 2023

module \9Sym_AMMES_crit_1  ( 
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
  INV        g000(.A(i1), .Y(new_n11_));
  INV        g001(.A(i6), .Y(new_n12_));
  INV        g002(.A(i7), .Y(new_n13_));
  INV        g003(.A(i4), .Y(new_n14_));
  NAi21      g004(.An(i8), .B(i5), .Y(new_n15_));
  NAi21      g005(.An(i0), .B(i3), .Y(new_n16_));
  AOI210     g006(.A0(new_n16_), .A1(new_n15_), .B0(new_n14_), .Y(new_n17_));
  INV        g007(.A(i3), .Y(new_n18_));
  NAi21      g008(.An(i4), .B(i8), .Y(new_n19_));
  NAi21      g009(.An(i5), .B(i2), .Y(new_n20_));
  AOI210     g010(.A0(new_n20_), .A1(new_n19_), .B0(new_n18_), .Y(new_n21_));
  OAI210     g011(.A0(new_n21_), .A1(new_n17_), .B0(new_n13_), .Y(new_n22_));
  NAi31      g012(.An(i0), .B(i8), .C(i5), .Y(new_n23_));
  NAi31      g013(.An(i8), .B(i7), .C(i4), .Y(new_n24_));
  AOI210     g014(.A0(new_n24_), .A1(new_n23_), .B0(i2), .Y(new_n25_));
  NAi31      g015(.An(i0), .B(i5), .C(i2), .Y(new_n26_));
  NAi31      g016(.An(i5), .B(i8), .C(i4), .Y(new_n27_));
  AOI210     g017(.A0(new_n27_), .A1(new_n26_), .B0(i3), .Y(new_n28_));
  NAi21      g018(.An(i5), .B(i8), .Y(new_n29_));
  NAi21      g019(.An(i0), .B(i2), .Y(new_n30_));
  NAi21      g020(.An(i4), .B(i7), .Y(new_n31_));
  AOI210     g021(.A0(new_n31_), .A1(new_n29_), .B0(new_n30_), .Y(new_n32_));
  NO3        g022(.A(new_n32_), .B(new_n28_), .C(new_n25_), .Y(new_n33_));
  AOI210     g023(.A0(new_n33_), .A1(new_n22_), .B0(new_n12_), .Y(new_n34_));
  INV        g024(.A(i2), .Y(new_n35_));
  NO2        g025(.A(i8), .B(i5), .Y(new_n36_));
  NO2        g026(.A(i4), .B(i3), .Y(new_n37_));
  OAI210     g027(.A0(new_n37_), .A1(new_n36_), .B0(i0), .Y(new_n38_));
  NAi21      g028(.An(i6), .B(i5), .Y(new_n39_));
  NA3        g029(.A(new_n39_), .B(new_n29_), .C(new_n15_), .Y(new_n40_));
  NOi21      g030(.An(i5), .B(i8), .Y(new_n41_));
  NOi21      g031(.An(i8), .B(i5), .Y(new_n42_));
  OAI210     g032(.A0(new_n42_), .A1(new_n41_), .B0(new_n18_), .Y(new_n43_));
  NA3        g033(.A(new_n43_), .B(new_n40_), .C(new_n14_), .Y(new_n44_));
  AOI210     g034(.A0(new_n44_), .A1(new_n38_), .B0(new_n35_), .Y(new_n45_));
  NAi21      g035(.An(i8), .B(i7), .Y(new_n46_));
  AOI210     g036(.A0(new_n39_), .A1(new_n46_), .B0(i0), .Y(new_n47_));
  INV        g037(.A(i8), .Y(new_n48_));
  NO3        g038(.A(new_n48_), .B(i7), .C(i3), .Y(new_n49_));
  OAI210     g039(.A0(new_n49_), .A1(new_n47_), .B0(i2), .Y(new_n50_));
  NOi21      g040(.An(i7), .B(i5), .Y(new_n51_));
  NO2        g041(.A(i6), .B(new_n18_), .Y(new_n52_));
  INV        g042(.A(i5), .Y(new_n53_));
  NO2        g043(.A(new_n53_), .B(i2), .Y(new_n54_));
  NOi21      g044(.An(i3), .B(i7), .Y(new_n55_));
  AO210      g045(.A0(i8), .A1(new_n18_), .B0(new_n55_), .Y(new_n56_));
  AOI220     g046(.A0(new_n56_), .A1(new_n54_), .B0(new_n52_), .B1(new_n51_), .Y(new_n57_));
  AOI210     g047(.A0(new_n57_), .A1(new_n50_), .B0(new_n14_), .Y(new_n58_));
  OAI210     g048(.A0(new_n13_), .A1(i2), .B0(new_n15_), .Y(new_n59_));
  NOi21      g049(.An(i7), .B(i3), .Y(new_n60_));
  AO210      g050(.A0(new_n60_), .A1(i8), .B0(i6), .Y(new_n61_));
  AOI210     g051(.A0(new_n59_), .A1(i3), .B0(new_n61_), .Y(new_n62_));
  NAi21      g052(.An(i5), .B(i4), .Y(new_n63_));
  AOI210     g053(.A0(new_n63_), .A1(new_n19_), .B0(i2), .Y(new_n64_));
  OR2        g054(.A(i8), .B(i3), .Y(new_n65_));
  OAI210     g055(.A0(new_n65_), .A1(new_n53_), .B0(i6), .Y(new_n66_));
  OAI210     g056(.A0(new_n66_), .A1(new_n64_), .B0(i0), .Y(new_n67_));
  NOi21      g057(.An(i5), .B(i7), .Y(new_n68_));
  NA3        g058(.A(new_n68_), .B(new_n52_), .C(i8), .Y(new_n69_));
  OAI210     g059(.A0(new_n67_), .A1(new_n62_), .B0(new_n69_), .Y(new_n70_));
  OR4        g060(.A(new_n70_), .B(new_n58_), .C(new_n45_), .D(new_n34_), .Y(new_n71_));
  NA2        g061(.A(new_n71_), .B(new_n11_), .Y(new_n72_));
  INV        g062(.A(i0), .Y(new_n73_));
  NO2        g063(.A(i5), .B(i3), .Y(new_n74_));
  NA2        g064(.A(i5), .B(i4), .Y(new_n75_));
  NOi31      g065(.An(new_n75_), .B(new_n74_), .C(new_n35_), .Y(new_n76_));
  NA2        g066(.A(i5), .B(new_n35_), .Y(new_n77_));
  NO2        g067(.A(new_n77_), .B(new_n14_), .Y(new_n78_));
  OAI210     g068(.A0(new_n78_), .A1(new_n76_), .B0(new_n73_), .Y(new_n79_));
  NOi21      g069(.An(i5), .B(i4), .Y(new_n80_));
  NA2        g070(.A(new_n35_), .B(i0), .Y(new_n81_));
  OAI210     g071(.A0(new_n48_), .A1(i3), .B0(new_n81_), .Y(new_n82_));
  NA2        g072(.A(new_n77_), .B(new_n29_), .Y(new_n83_));
  NO2        g073(.A(new_n14_), .B(new_n18_), .Y(new_n84_));
  NO2        g074(.A(new_n84_), .B(new_n37_), .Y(new_n85_));
  AOI220     g075(.A0(new_n85_), .A1(new_n83_), .B0(new_n82_), .B1(new_n80_), .Y(new_n86_));
  AOI210     g076(.A0(new_n86_), .A1(new_n79_), .B0(new_n13_), .Y(new_n87_));
  NOi21      g077(.An(i8), .B(i7), .Y(new_n88_));
  NOi21      g078(.An(i3), .B(i8), .Y(new_n89_));
  OAI210     g079(.A0(new_n89_), .A1(new_n88_), .B0(i0), .Y(new_n90_));
  OAI210     g080(.A0(new_n63_), .A1(new_n18_), .B0(new_n90_), .Y(new_n91_));
  NOi31      g081(.An(i8), .B(i7), .C(i0), .Y(new_n92_));
  NOi31      g082(.An(i5), .B(i8), .C(i4), .Y(new_n93_));
  OAI210     g083(.A0(new_n93_), .A1(new_n92_), .B0(i2), .Y(new_n94_));
  NOi21      g084(.An(i4), .B(i0), .Y(new_n95_));
  OAI210     g085(.A0(new_n60_), .A1(new_n41_), .B0(new_n95_), .Y(new_n96_));
  OAI210     g086(.A0(new_n68_), .A1(new_n51_), .B0(new_n89_), .Y(new_n97_));
  NA3        g087(.A(new_n97_), .B(new_n96_), .C(new_n94_), .Y(new_n98_));
  AOI210     g088(.A0(new_n91_), .A1(new_n35_), .B0(new_n98_), .Y(new_n99_));
  OAI210     g089(.A0(i7), .A1(i5), .B0(new_n65_), .Y(new_n100_));
  NA2        g090(.A(i8), .B(new_n13_), .Y(new_n101_));
  NO3        g091(.A(new_n84_), .B(new_n74_), .C(new_n101_), .Y(new_n102_));
  AOI210     g092(.A0(new_n100_), .A1(i2), .B0(new_n102_), .Y(new_n103_));
  OAI220     g093(.A0(new_n103_), .A1(new_n73_), .B0(new_n99_), .B1(new_n11_), .Y(new_n104_));
  OAI210     g094(.A0(new_n104_), .A1(new_n87_), .B0(new_n12_), .Y(new_n105_));
  AOI220     g095(.A0(i6), .A1(new_n53_), .B0(new_n14_), .B1(i2), .Y(new_n106_));
  NA3        g096(.A(new_n53_), .B(new_n14_), .C(i1), .Y(new_n107_));
  OAI210     g097(.A0(new_n106_), .A1(i0), .B0(new_n107_), .Y(new_n108_));
  NOi21      g098(.An(i1), .B(i8), .Y(new_n109_));
  AOI210     g099(.A0(i6), .A1(new_n14_), .B0(new_n109_), .Y(new_n110_));
  NO2        g100(.A(new_n110_), .B(new_n81_), .Y(new_n111_));
  AOI210     g101(.A0(new_n108_), .A1(i8), .B0(new_n111_), .Y(new_n112_));
  NOi21      g102(.An(i6), .B(i7), .Y(new_n113_));
  NA2        g103(.A(new_n113_), .B(new_n75_), .Y(new_n114_));
  AOI210     g104(.A0(i6), .A1(new_n53_), .B0(new_n80_), .Y(new_n115_));
  NA2        g105(.A(new_n48_), .B(i0), .Y(new_n116_));
  OAI220     g106(.A0(new_n116_), .A1(new_n115_), .B0(new_n114_), .B1(new_n36_), .Y(new_n117_));
  OAI210     g107(.A0(new_n68_), .A1(new_n42_), .B0(new_n35_), .Y(new_n118_));
  AOI210     g108(.A0(new_n109_), .A1(new_n13_), .B0(new_n73_), .Y(new_n119_));
  NA2        g109(.A(new_n119_), .B(new_n118_), .Y(new_n120_));
  OAI210     g110(.A0(new_n113_), .A1(new_n109_), .B0(i2), .Y(new_n121_));
  NOi21      g111(.An(i1), .B(i2), .Y(new_n122_));
  AOI210     g112(.A0(new_n122_), .A1(i8), .B0(i0), .Y(new_n123_));
  AOI210     g113(.A0(new_n123_), .A1(new_n121_), .B0(new_n14_), .Y(new_n124_));
  AOI220     g114(.A0(new_n124_), .A1(new_n120_), .B0(new_n117_), .B1(i1), .Y(new_n125_));
  OAI210     g115(.A0(new_n112_), .A1(new_n13_), .B0(new_n125_), .Y(new_n126_));
  NA3        g116(.A(new_n41_), .B(i6), .C(new_n14_), .Y(new_n127_));
  OAI210     g117(.A0(new_n63_), .A1(new_n30_), .B0(new_n127_), .Y(new_n128_));
  AOI220     g118(.A0(new_n128_), .A1(new_n55_), .B0(new_n126_), .B1(new_n18_), .Y(new_n129_));
  OA210      g119(.A0(i8), .A1(new_n12_), .B0(new_n63_), .Y(new_n130_));
  OAI220     g120(.A0(new_n130_), .A1(new_n73_), .B0(new_n19_), .B1(new_n11_), .Y(new_n131_));
  NA2        g121(.A(new_n95_), .B(i8), .Y(new_n132_));
  NA3        g122(.A(i7), .B(i6), .C(new_n14_), .Y(new_n133_));
  AOI210     g123(.A0(new_n133_), .A1(new_n132_), .B0(i5), .Y(new_n134_));
  AOI210     g124(.A0(new_n131_), .A1(new_n13_), .B0(new_n134_), .Y(new_n135_));
  NOi21      g125(.An(i1), .B(i0), .Y(new_n136_));
  AOI220     g126(.A0(new_n136_), .A1(i6), .B0(new_n51_), .B1(i0), .Y(new_n137_));
  OAI220     g127(.A0(new_n137_), .A1(new_n14_), .B0(new_n133_), .B1(new_n53_), .Y(new_n138_));
  NA2        g128(.A(new_n136_), .B(i5), .Y(new_n139_));
  AOI210     g129(.A0(new_n101_), .A1(new_n31_), .B0(new_n139_), .Y(new_n140_));
  AOI210     g130(.A0(new_n138_), .A1(new_n48_), .B0(new_n140_), .Y(new_n141_));
  OAI210     g131(.A0(new_n135_), .A1(new_n18_), .B0(new_n141_), .Y(new_n142_));
  AO210      g132(.A0(new_n31_), .A1(new_n30_), .B0(i8), .Y(new_n143_));
  NOi21      g133(.An(i2), .B(i0), .Y(new_n144_));
  OAI210     g134(.A0(new_n55_), .A1(new_n144_), .B0(new_n14_), .Y(new_n145_));
  AOI210     g135(.A0(new_n145_), .A1(new_n143_), .B0(new_n12_), .Y(new_n146_));
  NOi31      g136(.An(new_n89_), .B(i7), .C(new_n35_), .Y(new_n147_));
  OAI210     g137(.A0(new_n147_), .A1(new_n146_), .B0(new_n53_), .Y(new_n148_));
  NAi21      g138(.An(new_n113_), .B(new_n46_), .Y(new_n149_));
  AOI220     g139(.A0(new_n149_), .A1(new_n14_), .B0(new_n113_), .B1(new_n48_), .Y(new_n150_));
  OAI210     g140(.A0(new_n150_), .A1(new_n30_), .B0(new_n148_), .Y(new_n151_));
  AOI220     g141(.A0(new_n151_), .A1(i1), .B0(new_n142_), .B1(new_n35_), .Y(new_n152_));
  NA4        g142(.A(new_n152_), .B(new_n129_), .C(new_n105_), .D(new_n72_), .Y(zori0));
endmodule


