// Benchmark "9sym/9sym_1_4_0_esp" written by ABC on Wed Mar  8 02:26:28 2023

module \9sym/9sym_1_4_0_esp  ( 
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
    new_n150_;
  INV        g000(.A(i5), .Y(new_n11_));
  NAi21      g001(.An(i6), .B(i0), .Y(new_n12_));
  NAi21      g002(.An(i3), .B(i8), .Y(new_n13_));
  NA2        g003(.A(new_n13_), .B(new_n12_), .Y(new_n14_));
  NA3        g004(.A(new_n14_), .B(i7), .C(new_n11_), .Y(new_n15_));
  NAi21      g005(.An(i7), .B(i4), .Y(new_n16_));
  NAi21      g006(.An(i0), .B(i3), .Y(new_n17_));
  NO2        g007(.A(i8), .B(i7), .Y(new_n18_));
  AO210      g008(.A0(i7), .A1(i4), .B0(new_n18_), .Y(new_n19_));
  OA220      g009(.A0(new_n19_), .A1(new_n17_), .B0(new_n16_), .B1(new_n12_), .Y(new_n20_));
  AOI210     g010(.A0(new_n20_), .A1(new_n15_), .B0(i1), .Y(new_n21_));
  NAi21      g011(.An(i5), .B(i8), .Y(new_n22_));
  NAi21      g012(.An(i3), .B(i5), .Y(new_n23_));
  OAI210     g013(.A0(new_n23_), .A1(i7), .B0(new_n22_), .Y(new_n24_));
  NO2        g014(.A(i6), .B(new_n11_), .Y(new_n25_));
  AOI220     g015(.A0(new_n25_), .A1(i7), .B0(new_n24_), .B1(i4), .Y(new_n26_));
  INV        g016(.A(i8), .Y(new_n27_));
  INV        g017(.A(i0), .Y(new_n28_));
  OAI220     g018(.A0(i7), .A1(new_n28_), .B0(i6), .B1(new_n11_), .Y(new_n29_));
  NAi21      g019(.An(i4), .B(i3), .Y(new_n30_));
  OAI220     g020(.A0(new_n30_), .A1(i8), .B0(new_n13_), .B1(i1), .Y(new_n31_));
  NAi32      g021(.An(i3), .Bn(i1), .C(i5), .Y(new_n32_));
  NAi31      g022(.An(i5), .B(i4), .C(i3), .Y(new_n33_));
  OAI220     g023(.A0(new_n33_), .A1(i6), .B0(new_n32_), .B1(new_n28_), .Y(new_n34_));
  AOI220     g024(.A0(new_n34_), .A1(new_n27_), .B0(new_n31_), .B1(new_n29_), .Y(new_n35_));
  OAI210     g025(.A0(new_n26_), .A1(i0), .B0(new_n35_), .Y(new_n36_));
  OAI210     g026(.A0(new_n36_), .A1(new_n21_), .B0(i2), .Y(new_n37_));
  NAi21      g027(.An(i6), .B(i7), .Y(new_n38_));
  NAi21      g028(.An(i2), .B(i8), .Y(new_n39_));
  OAI210     g029(.A0(new_n39_), .A1(i1), .B0(new_n38_), .Y(new_n40_));
  NA2        g030(.A(new_n40_), .B(new_n28_), .Y(new_n41_));
  INV        g031(.A(i4), .Y(new_n42_));
  NO2        g032(.A(i8), .B(new_n42_), .Y(new_n43_));
  NO2        g033(.A(i2), .B(new_n28_), .Y(new_n44_));
  NO2        g034(.A(i7), .B(i6), .Y(new_n45_));
  OAI210     g035(.A0(new_n44_), .A1(new_n43_), .B0(new_n45_), .Y(new_n46_));
  AOI210     g036(.A0(new_n46_), .A1(new_n41_), .B0(new_n11_), .Y(new_n47_));
  NO2        g037(.A(new_n12_), .B(i4), .Y(new_n48_));
  NO2        g038(.A(new_n22_), .B(i2), .Y(new_n49_));
  INV        g039(.A(i7), .Y(new_n50_));
  NO2        g040(.A(new_n50_), .B(i1), .Y(new_n51_));
  OAI210     g041(.A0(new_n49_), .A1(new_n48_), .B0(new_n51_), .Y(new_n52_));
  NO2        g042(.A(new_n27_), .B(i7), .Y(new_n53_));
  NA2        g043(.A(new_n53_), .B(new_n48_), .Y(new_n54_));
  INV        g044(.A(i2), .Y(new_n55_));
  NO2        g045(.A(i5), .B(new_n42_), .Y(new_n56_));
  NAi21      g046(.An(i7), .B(i8), .Y(new_n57_));
  NA2        g047(.A(new_n57_), .B(new_n12_), .Y(new_n58_));
  NA3        g048(.A(new_n58_), .B(new_n56_), .C(new_n55_), .Y(new_n59_));
  NA3        g049(.A(new_n59_), .B(new_n54_), .C(new_n52_), .Y(new_n60_));
  OAI210     g050(.A0(new_n60_), .A1(new_n47_), .B0(i3), .Y(new_n61_));
  NAi21      g051(.An(i8), .B(i5), .Y(new_n62_));
  OAI220     g052(.A0(new_n62_), .A1(new_n42_), .B0(new_n38_), .B1(new_n27_), .Y(new_n63_));
  NO2        g053(.A(new_n11_), .B(i4), .Y(new_n64_));
  NA2        g054(.A(i7), .B(i3), .Y(new_n65_));
  NOi21      g055(.An(new_n65_), .B(new_n18_), .Y(new_n66_));
  AOI220     g056(.A0(new_n66_), .A1(new_n64_), .B0(new_n63_), .B1(new_n55_), .Y(new_n67_));
  INV        g057(.A(i6), .Y(new_n68_));
  NA3        g058(.A(new_n53_), .B(new_n56_), .C(new_n68_), .Y(new_n69_));
  OAI210     g059(.A0(new_n67_), .A1(i1), .B0(new_n69_), .Y(new_n70_));
  NO4        g060(.A(i8), .B(new_n50_), .C(i5), .D(new_n42_), .Y(new_n71_));
  NAi31      g061(.An(i3), .B(i8), .C(i5), .Y(new_n72_));
  AOI210     g062(.A0(new_n38_), .A1(new_n16_), .B0(new_n72_), .Y(new_n73_));
  OAI210     g063(.A0(new_n73_), .A1(new_n71_), .B0(new_n55_), .Y(new_n74_));
  NO2        g064(.A(i5), .B(i3), .Y(new_n75_));
  NO2        g065(.A(i8), .B(i1), .Y(new_n76_));
  NO2        g066(.A(new_n38_), .B(new_n42_), .Y(new_n77_));
  OAI210     g067(.A0(new_n76_), .A1(new_n75_), .B0(new_n77_), .Y(new_n78_));
  NA2        g068(.A(new_n78_), .B(new_n74_), .Y(new_n79_));
  AOI210     g069(.A0(new_n70_), .A1(i0), .B0(new_n79_), .Y(new_n80_));
  NA3        g070(.A(new_n80_), .B(new_n61_), .C(new_n37_), .Y(new_n81_));
  INV        g071(.A(i1), .Y(new_n82_));
  NAi21      g072(.An(i4), .B(i5), .Y(new_n83_));
  AOI210     g073(.A0(new_n83_), .A1(new_n13_), .B0(i2), .Y(new_n84_));
  NO2        g074(.A(i8), .B(i5), .Y(new_n85_));
  NOi21      g075(.An(new_n85_), .B(new_n28_), .Y(new_n86_));
  OAI210     g076(.A0(new_n86_), .A1(new_n84_), .B0(new_n82_), .Y(new_n87_));
  NA2        g077(.A(new_n30_), .B(new_n55_), .Y(new_n88_));
  AOI210     g078(.A0(i5), .A1(i2), .B0(i0), .Y(new_n89_));
  NO2        g079(.A(i4), .B(i3), .Y(new_n90_));
  NO2        g080(.A(i8), .B(new_n82_), .Y(new_n91_));
  AOI220     g081(.A0(new_n91_), .A1(new_n90_), .B0(new_n89_), .B1(new_n88_), .Y(new_n92_));
  AOI210     g082(.A0(new_n92_), .A1(new_n87_), .B0(new_n50_), .Y(new_n93_));
  AOI220     g083(.A0(new_n76_), .A1(new_n28_), .B0(new_n75_), .B1(new_n50_), .Y(new_n94_));
  NO2        g084(.A(new_n94_), .B(new_n42_), .Y(new_n95_));
  NO2        g085(.A(new_n13_), .B(i2), .Y(new_n96_));
  NOi21      g086(.An(new_n76_), .B(new_n28_), .Y(new_n97_));
  OAI210     g087(.A0(new_n97_), .A1(new_n96_), .B0(i5), .Y(new_n98_));
  INV        g088(.A(i3), .Y(new_n99_));
  NO2        g089(.A(i5), .B(new_n99_), .Y(new_n100_));
  OAI210     g090(.A0(i2), .A1(new_n82_), .B0(new_n57_), .Y(new_n101_));
  OAI210     g091(.A0(new_n11_), .A1(i2), .B0(new_n13_), .Y(new_n102_));
  NO2        g092(.A(i7), .B(new_n82_), .Y(new_n103_));
  AOI220     g093(.A0(new_n103_), .A1(new_n102_), .B0(new_n101_), .B1(new_n100_), .Y(new_n104_));
  AOI210     g094(.A0(new_n104_), .A1(new_n98_), .B0(i4), .Y(new_n105_));
  NO3        g095(.A(new_n105_), .B(new_n95_), .C(new_n93_), .Y(new_n106_));
  NO2        g096(.A(new_n23_), .B(i1), .Y(new_n107_));
  NO3        g097(.A(i8), .B(i7), .C(new_n82_), .Y(new_n108_));
  OAI210     g098(.A0(new_n108_), .A1(new_n107_), .B0(new_n28_), .Y(new_n109_));
  NOi21      g099(.An(i8), .B(i5), .Y(new_n110_));
  AOI220     g100(.A0(new_n110_), .A1(new_n42_), .B0(new_n18_), .B1(i3), .Y(new_n111_));
  NA3        g101(.A(new_n90_), .B(new_n11_), .C(i0), .Y(new_n112_));
  OA210      g102(.A0(new_n111_), .A1(i1), .B0(new_n112_), .Y(new_n113_));
  AOI210     g103(.A0(new_n113_), .A1(new_n109_), .B0(new_n55_), .Y(new_n114_));
  OAI210     g104(.A0(i5), .A1(new_n99_), .B0(new_n39_), .Y(new_n115_));
  AOI220     g105(.A0(new_n115_), .A1(new_n82_), .B0(new_n91_), .B1(new_n11_), .Y(new_n116_));
  OR2        g106(.A(new_n62_), .B(new_n99_), .Y(new_n117_));
  OAI220     g107(.A0(new_n117_), .A1(i2), .B0(new_n116_), .B1(new_n28_), .Y(new_n118_));
  AOI210     g108(.A0(new_n118_), .A1(new_n50_), .B0(new_n114_), .Y(new_n119_));
  AOI210     g109(.A0(new_n119_), .A1(new_n106_), .B0(new_n68_), .Y(new_n120_));
  NAi21      g110(.An(i0), .B(i5), .Y(new_n121_));
  AOI210     g111(.A0(new_n121_), .A1(new_n38_), .B0(i4), .Y(new_n122_));
  NAi21      g112(.An(i5), .B(i4), .Y(new_n123_));
  AOI210     g113(.A0(new_n123_), .A1(new_n12_), .B0(i8), .Y(new_n124_));
  OAI210     g114(.A0(new_n124_), .A1(new_n122_), .B0(i2), .Y(new_n125_));
  NA2        g115(.A(i8), .B(i6), .Y(new_n126_));
  NO3        g116(.A(new_n85_), .B(i7), .C(new_n28_), .Y(new_n127_));
  NA2        g117(.A(new_n62_), .B(new_n39_), .Y(new_n128_));
  NO2        g118(.A(new_n42_), .B(i0), .Y(new_n129_));
  AOI220     g119(.A0(new_n129_), .A1(new_n128_), .B0(new_n127_), .B1(new_n126_), .Y(new_n130_));
  AOI210     g120(.A0(new_n130_), .A1(new_n125_), .B0(i3), .Y(new_n131_));
  NA3        g121(.A(i8), .B(new_n50_), .C(i2), .Y(new_n132_));
  AOI210     g122(.A0(new_n132_), .A1(new_n117_), .B0(i0), .Y(new_n133_));
  NA3        g123(.A(i8), .B(i7), .C(new_n68_), .Y(new_n134_));
  NA3        g124(.A(new_n27_), .B(i3), .C(i2), .Y(new_n135_));
  AOI210     g125(.A0(new_n135_), .A1(new_n134_), .B0(i5), .Y(new_n136_));
  OA210      g126(.A0(new_n136_), .A1(new_n133_), .B0(new_n42_), .Y(new_n137_));
  NO2        g127(.A(i6), .B(new_n42_), .Y(new_n138_));
  NA2        g128(.A(new_n30_), .B(i0), .Y(new_n139_));
  AOI210     g129(.A0(new_n65_), .A1(new_n28_), .B0(i8), .Y(new_n140_));
  OAI210     g130(.A0(new_n139_), .A1(new_n138_), .B0(new_n140_), .Y(new_n141_));
  OAI210     g131(.A0(i8), .A1(new_n28_), .B0(new_n22_), .Y(new_n142_));
  NO2        g132(.A(new_n50_), .B(i3), .Y(new_n143_));
  OAI220     g133(.A0(new_n83_), .A1(new_n38_), .B0(new_n57_), .B1(new_n17_), .Y(new_n144_));
  AOI210     g134(.A0(new_n143_), .A1(new_n142_), .B0(new_n144_), .Y(new_n145_));
  AOI210     g135(.A0(new_n145_), .A1(new_n141_), .B0(i2), .Y(new_n146_));
  NA2        g136(.A(new_n25_), .B(i8), .Y(new_n147_));
  AOI210     g137(.A0(new_n147_), .A1(new_n33_), .B0(i0), .Y(new_n148_));
  NO4        g138(.A(new_n148_), .B(new_n146_), .C(new_n137_), .D(new_n131_), .Y(new_n149_));
  NO2        g139(.A(new_n149_), .B(new_n82_), .Y(new_n150_));
  OR3        g140(.A(new_n150_), .B(new_n120_), .C(new_n81_), .Y(zori0));
endmodule


