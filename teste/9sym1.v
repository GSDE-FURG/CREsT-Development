// Benchmark "9sym/9sym1_esp" written by ABC on Sun Oct 10 00:28:58 2021

module \9sym/9sym1_esp  ( 
    i0, i1, i2, i3, i4, i5, i6, i7, i8,
    zori10  );
  input  i0, i1, i2, i3, i4, i5, i6, i7, i8;
  output zori10;
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
  NO2        g001(.A(new_n11_), .B(i3), .Y(new_n12_));
  INV        g002(.A(i7), .Y(new_n13_));
  NA2        g003(.A(i8), .B(new_n13_), .Y(new_n14_));
  NOi21      g004(.An(new_n14_), .B(new_n12_), .Y(new_n15_));
  NO2        g005(.A(new_n15_), .B(i0), .Y(new_n16_));
  INV        g006(.A(i3), .Y(new_n17_));
  INV        g007(.A(i6), .Y(new_n18_));
  NA2        g008(.A(new_n18_), .B(new_n17_), .Y(new_n19_));
  NO2        g009(.A(new_n19_), .B(new_n13_), .Y(new_n20_));
  OAI210     g010(.A0(new_n20_), .A1(new_n16_), .B0(i1), .Y(new_n21_));
  INV        g011(.A(i0), .Y(new_n22_));
  INV        g012(.A(i8), .Y(new_n23_));
  NO2        g013(.A(i7), .B(new_n17_), .Y(new_n24_));
  NA2        g014(.A(new_n24_), .B(new_n23_), .Y(new_n25_));
  NO2        g015(.A(new_n18_), .B(i5), .Y(new_n26_));
  NA2        g016(.A(new_n26_), .B(new_n17_), .Y(new_n27_));
  AOI210     g017(.A0(new_n27_), .A1(new_n25_), .B0(new_n22_), .Y(new_n28_));
  NA2        g018(.A(new_n26_), .B(i8), .Y(new_n29_));
  NA3        g019(.A(i7), .B(i3), .C(new_n22_), .Y(new_n30_));
  AOI210     g020(.A0(new_n30_), .A1(new_n29_), .B0(i1), .Y(new_n31_));
  NO4        g021(.A(new_n13_), .B(new_n18_), .C(i5), .D(i0), .Y(new_n32_));
  NAi21      g022(.An(i6), .B(i5), .Y(new_n33_));
  NA2        g023(.A(new_n11_), .B(i1), .Y(new_n34_));
  NA2        g024(.A(new_n23_), .B(i3), .Y(new_n35_));
  AOI210     g025(.A0(new_n34_), .A1(new_n33_), .B0(new_n35_), .Y(new_n36_));
  NO4        g026(.A(new_n36_), .B(new_n32_), .C(new_n31_), .D(new_n28_), .Y(new_n37_));
  AOI210     g027(.A0(new_n37_), .A1(new_n21_), .B0(i4), .Y(new_n38_));
  INV        g028(.A(i1), .Y(new_n39_));
  NO2        g029(.A(new_n23_), .B(i3), .Y(new_n40_));
  INV        g030(.A(i4), .Y(new_n41_));
  NO2        g031(.A(i6), .B(new_n41_), .Y(new_n42_));
  OAI210     g032(.A0(new_n42_), .A1(new_n40_), .B0(i0), .Y(new_n43_));
  NO2        g033(.A(i8), .B(new_n18_), .Y(new_n44_));
  NO2        g034(.A(new_n23_), .B(i0), .Y(new_n45_));
  OAI210     g035(.A0(new_n45_), .A1(new_n44_), .B0(i3), .Y(new_n46_));
  AOI210     g036(.A0(new_n46_), .A1(new_n43_), .B0(i7), .Y(new_n47_));
  NAi21      g037(.An(i5), .B(i4), .Y(new_n48_));
  AOI210     g038(.A0(new_n48_), .A1(new_n33_), .B0(i0), .Y(new_n49_));
  AOI210     g039(.A0(new_n40_), .A1(new_n11_), .B0(new_n49_), .Y(new_n50_));
  NAi21      g040(.An(i6), .B(i7), .Y(new_n51_));
  NAi21      g041(.An(i8), .B(i5), .Y(new_n52_));
  OAI220     g042(.A0(new_n52_), .A1(i3), .B0(new_n51_), .B1(i5), .Y(new_n53_));
  NAi21      g043(.An(i6), .B(i8), .Y(new_n54_));
  OAI210     g044(.A0(new_n18_), .A1(i0), .B0(new_n54_), .Y(new_n55_));
  AOI220     g045(.A0(new_n55_), .A1(new_n12_), .B0(new_n53_), .B1(i0), .Y(new_n56_));
  OAI210     g046(.A0(new_n50_), .A1(new_n13_), .B0(new_n56_), .Y(new_n57_));
  OAI210     g047(.A0(new_n57_), .A1(new_n47_), .B0(new_n39_), .Y(new_n58_));
  NO2        g048(.A(i8), .B(new_n17_), .Y(new_n59_));
  AOI220     g049(.A0(new_n45_), .A1(new_n13_), .B0(new_n59_), .B1(new_n18_), .Y(new_n60_));
  OAI220     g050(.A0(i7), .A1(new_n11_), .B0(i6), .B1(new_n39_), .Y(new_n61_));
  AOI220     g051(.A0(new_n61_), .A1(new_n22_), .B0(new_n44_), .B1(new_n13_), .Y(new_n62_));
  OAI220     g052(.A0(new_n62_), .A1(i3), .B0(new_n60_), .B1(i5), .Y(new_n63_));
  NA2        g053(.A(new_n23_), .B(i0), .Y(new_n64_));
  OR2        g054(.A(new_n64_), .B(new_n19_), .Y(new_n65_));
  NO2        g055(.A(new_n23_), .B(i6), .Y(new_n66_));
  NO2        g056(.A(i7), .B(i0), .Y(new_n67_));
  OAI210     g057(.A0(new_n66_), .A1(new_n44_), .B0(new_n67_), .Y(new_n68_));
  AOI210     g058(.A0(new_n68_), .A1(new_n65_), .B0(new_n39_), .Y(new_n69_));
  AOI210     g059(.A0(new_n63_), .A1(i4), .B0(new_n69_), .Y(new_n70_));
  NA2        g060(.A(new_n70_), .B(new_n58_), .Y(new_n71_));
  OAI210     g061(.A0(new_n71_), .A1(new_n38_), .B0(i2), .Y(new_n72_));
  AOI210     g062(.A0(new_n54_), .A1(new_n35_), .B0(i4), .Y(new_n73_));
  NO3        g063(.A(i8), .B(new_n41_), .C(i3), .Y(new_n74_));
  OAI210     g064(.A0(new_n74_), .A1(new_n73_), .B0(i5), .Y(new_n75_));
  NO2        g065(.A(new_n17_), .B(i2), .Y(new_n76_));
  NO2        g066(.A(i8), .B(new_n13_), .Y(new_n77_));
  NAi21      g067(.An(new_n77_), .B(new_n14_), .Y(new_n78_));
  NA2        g068(.A(new_n11_), .B(i3), .Y(new_n79_));
  INV        g069(.A(i2), .Y(new_n80_));
  NO2        g070(.A(new_n13_), .B(i5), .Y(new_n81_));
  AOI210     g071(.A0(i8), .A1(new_n80_), .B0(new_n81_), .Y(new_n82_));
  OAI220     g072(.A0(new_n82_), .A1(i3), .B0(new_n79_), .B1(i6), .Y(new_n83_));
  AOI220     g073(.A0(new_n83_), .A1(i4), .B0(new_n78_), .B1(new_n76_), .Y(new_n84_));
  AOI210     g074(.A0(new_n84_), .A1(new_n75_), .B0(new_n39_), .Y(new_n85_));
  NO2        g075(.A(new_n11_), .B(i1), .Y(new_n86_));
  NOi21      g076(.An(new_n34_), .B(new_n86_), .Y(new_n87_));
  AOI220     g077(.A0(new_n76_), .A1(i8), .B0(new_n44_), .B1(i4), .Y(new_n88_));
  NA3        g078(.A(i6), .B(new_n41_), .C(i3), .Y(new_n89_));
  OAI210     g079(.A0(new_n33_), .A1(new_n41_), .B0(new_n89_), .Y(new_n90_));
  NO2        g080(.A(new_n35_), .B(new_n33_), .Y(new_n91_));
  AOI210     g081(.A0(new_n90_), .A1(new_n80_), .B0(new_n91_), .Y(new_n92_));
  OAI220     g082(.A0(new_n92_), .A1(new_n13_), .B0(new_n88_), .B1(new_n87_), .Y(new_n93_));
  OAI210     g083(.A0(new_n93_), .A1(new_n85_), .B0(new_n22_), .Y(new_n94_));
  NO2        g084(.A(i8), .B(new_n22_), .Y(new_n95_));
  OAI210     g085(.A0(new_n95_), .A1(new_n26_), .B0(i3), .Y(new_n96_));
  NO2        g086(.A(i7), .B(i6), .Y(new_n97_));
  NA2        g087(.A(i7), .B(i6), .Y(new_n98_));
  NOi21      g088(.An(new_n98_), .B(new_n97_), .Y(new_n99_));
  NA2        g089(.A(new_n99_), .B(i5), .Y(new_n100_));
  AO210      g090(.A0(new_n100_), .A1(new_n96_), .B0(i4), .Y(new_n101_));
  NA2        g091(.A(new_n81_), .B(i8), .Y(new_n102_));
  OAI210     g092(.A0(new_n97_), .A1(new_n64_), .B0(new_n102_), .Y(new_n103_));
  AOI220     g093(.A0(new_n103_), .A1(new_n17_), .B0(new_n95_), .B1(new_n42_), .Y(new_n104_));
  AOI210     g094(.A0(new_n104_), .A1(new_n101_), .B0(new_n39_), .Y(new_n105_));
  AOI210     g095(.A0(i7), .A1(new_n39_), .B0(new_n40_), .Y(new_n106_));
  OAI210     g096(.A0(new_n106_), .A1(i4), .B0(new_n25_), .Y(new_n107_));
  NO2        g097(.A(i8), .B(new_n41_), .Y(new_n108_));
  AOI220     g098(.A0(new_n108_), .A1(new_n39_), .B0(new_n24_), .B1(new_n18_), .Y(new_n109_));
  OAI210     g099(.A0(i7), .A1(new_n41_), .B0(new_n51_), .Y(new_n110_));
  NA2        g100(.A(new_n110_), .B(new_n40_), .Y(new_n111_));
  OAI210     g101(.A0(new_n109_), .A1(new_n22_), .B0(new_n111_), .Y(new_n112_));
  AOI210     g102(.A0(new_n107_), .A1(i6), .B0(new_n112_), .Y(new_n113_));
  NO3        g103(.A(i6), .B(i5), .C(new_n17_), .Y(new_n114_));
  NO3        g104(.A(i8), .B(new_n18_), .C(i1), .Y(new_n115_));
  OAI210     g105(.A0(new_n115_), .A1(new_n114_), .B0(i4), .Y(new_n116_));
  NA3        g106(.A(new_n99_), .B(i8), .C(new_n39_), .Y(new_n117_));
  AOI210     g107(.A0(new_n117_), .A1(new_n116_), .B0(new_n22_), .Y(new_n118_));
  NO2        g108(.A(i5), .B(new_n41_), .Y(new_n119_));
  NA2        g109(.A(new_n119_), .B(new_n59_), .Y(new_n120_));
  NA2        g110(.A(i5), .B(i3), .Y(new_n121_));
  NA4        g111(.A(new_n121_), .B(new_n19_), .C(i8), .D(new_n39_), .Y(new_n122_));
  AOI210     g112(.A0(new_n122_), .A1(new_n120_), .B0(new_n13_), .Y(new_n123_));
  NO4        g113(.A(new_n79_), .B(new_n23_), .C(i7), .D(new_n41_), .Y(new_n124_));
  NO3        g114(.A(new_n124_), .B(new_n123_), .C(new_n118_), .Y(new_n125_));
  OAI210     g115(.A0(new_n113_), .A1(new_n11_), .B0(new_n125_), .Y(new_n126_));
  OAI210     g116(.A0(new_n126_), .A1(new_n105_), .B0(new_n80_), .Y(new_n127_));
  AOI210     g117(.A0(i7), .A1(new_n17_), .B0(new_n44_), .Y(new_n128_));
  OAI220     g118(.A0(new_n128_), .A1(new_n11_), .B0(new_n51_), .B1(new_n17_), .Y(new_n129_));
  OAI210     g119(.A0(new_n42_), .A1(new_n26_), .B0(new_n77_), .Y(new_n130_));
  NO2        g120(.A(new_n23_), .B(i4), .Y(new_n131_));
  AOI220     g121(.A0(new_n131_), .A1(i5), .B0(new_n26_), .B1(i3), .Y(new_n132_));
  OAI210     g122(.A0(new_n132_), .A1(i7), .B0(new_n130_), .Y(new_n133_));
  AOI210     g123(.A0(new_n129_), .A1(new_n41_), .B0(new_n133_), .Y(new_n134_));
  OAI210     g124(.A0(i4), .A1(new_n17_), .B0(new_n48_), .Y(new_n135_));
  NA2        g125(.A(new_n54_), .B(new_n52_), .Y(new_n136_));
  NO2        g126(.A(i3), .B(new_n39_), .Y(new_n137_));
  AOI220     g127(.A0(new_n137_), .A1(new_n136_), .B0(new_n135_), .B1(new_n66_), .Y(new_n138_));
  OAI220     g128(.A0(new_n138_), .A1(i7), .B0(new_n134_), .B1(i1), .Y(new_n139_));
  NA2        g129(.A(new_n41_), .B(i1), .Y(new_n140_));
  NOi21      g130(.An(new_n140_), .B(new_n119_), .Y(new_n141_));
  NA2        g131(.A(new_n78_), .B(new_n17_), .Y(new_n142_));
  NO2        g132(.A(new_n79_), .B(i7), .Y(new_n143_));
  OAI210     g133(.A0(new_n131_), .A1(new_n108_), .B0(new_n143_), .Y(new_n144_));
  OAI210     g134(.A0(new_n142_), .A1(new_n141_), .B0(new_n144_), .Y(new_n145_));
  AOI210     g135(.A0(new_n13_), .A1(i5), .B0(new_n40_), .Y(new_n146_));
  NA2        g136(.A(new_n15_), .B(i4), .Y(new_n147_));
  OAI220     g137(.A0(new_n147_), .A1(new_n146_), .B0(new_n140_), .B1(new_n102_), .Y(new_n148_));
  MUX2       g138(.S(i6), .A(new_n148_), .B(new_n145_), .Y(new_n149_));
  AOI210     g139(.A0(new_n139_), .A1(i0), .B0(new_n149_), .Y(new_n150_));
  NA4        g140(.A(new_n150_), .B(new_n127_), .C(new_n94_), .D(new_n72_), .Y(zori10));
endmodule


