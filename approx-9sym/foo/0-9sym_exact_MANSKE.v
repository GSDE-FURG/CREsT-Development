// Benchmark "teste2-ESPRESSO2" written by ABC on Fri Jul 28 06:07:23 2023

module \teste2-ESPRESSO2  ( 
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
    new_n144_, new_n145_;
  INV        g000(.A(i5), .Y(new_n11_));
  NOi21      g001(.An(i3), .B(i8), .Y(new_n12_));
  NOi21      g002(.An(i2), .B(i6), .Y(new_n13_));
  OAI210     g003(.A0(new_n13_), .A1(new_n12_), .B0(new_n11_), .Y(new_n14_));
  INV        g004(.A(i2), .Y(new_n15_));
  NOi21      g005(.An(i7), .B(i6), .Y(new_n16_));
  NOi21      g006(.An(i8), .B(i7), .Y(new_n17_));
  OAI210     g007(.A0(new_n17_), .A1(new_n16_), .B0(new_n15_), .Y(new_n18_));
  INV        g008(.A(i6), .Y(new_n19_));
  INV        g009(.A(i8), .Y(new_n20_));
  NA3        g010(.A(new_n20_), .B(new_n19_), .C(i5), .Y(new_n21_));
  NA3        g011(.A(new_n21_), .B(new_n18_), .C(new_n14_), .Y(new_n22_));
  NA2        g012(.A(new_n22_), .B(i4), .Y(new_n23_));
  INV        g013(.A(i7), .Y(new_n24_));
  AOI210     g014(.A0(new_n24_), .A1(i3), .B0(i2), .Y(new_n25_));
  NAi21      g015(.An(i8), .B(i3), .Y(new_n26_));
  NAi21      g016(.An(i3), .B(i5), .Y(new_n27_));
  AOI210     g017(.A0(new_n27_), .A1(new_n26_), .B0(new_n19_), .Y(new_n28_));
  OA210      g018(.A0(new_n28_), .A1(new_n12_), .B0(new_n25_), .Y(new_n29_));
  INV        g019(.A(i4), .Y(new_n30_));
  NOi21      g020(.An(i6), .B(i3), .Y(new_n31_));
  NOi21      g021(.An(i8), .B(i6), .Y(new_n32_));
  OAI210     g022(.A0(new_n32_), .A1(new_n31_), .B0(new_n30_), .Y(new_n33_));
  INV        g023(.A(i3), .Y(new_n34_));
  NOi21      g024(.An(i5), .B(i7), .Y(new_n35_));
  OAI210     g025(.A0(new_n35_), .A1(new_n16_), .B0(new_n34_), .Y(new_n36_));
  AOI210     g026(.A0(new_n36_), .A1(new_n33_), .B0(new_n15_), .Y(new_n37_));
  NA2        g027(.A(i6), .B(new_n34_), .Y(new_n38_));
  NA2        g028(.A(new_n20_), .B(i7), .Y(new_n39_));
  NOi21      g029(.An(i2), .B(i8), .Y(new_n40_));
  AOI220     g030(.A0(new_n40_), .A1(new_n30_), .B0(new_n35_), .B1(new_n19_), .Y(new_n41_));
  OAI220     g031(.A0(new_n41_), .A1(new_n34_), .B0(new_n39_), .B1(new_n38_), .Y(new_n42_));
  NO3        g032(.A(new_n42_), .B(new_n37_), .C(new_n29_), .Y(new_n43_));
  AOI210     g033(.A0(new_n43_), .A1(new_n23_), .B0(i1), .Y(new_n44_));
  OAI210     g034(.A0(i8), .A1(i7), .B0(i2), .Y(new_n45_));
  NA2        g035(.A(new_n45_), .B(new_n30_), .Y(new_n46_));
  AOI210     g036(.A0(new_n39_), .A1(new_n25_), .B0(new_n46_), .Y(new_n47_));
  AOI220     g037(.A0(new_n24_), .A1(new_n11_), .B0(new_n30_), .B1(new_n15_), .Y(new_n48_));
  NA2        g038(.A(new_n11_), .B(new_n30_), .Y(new_n49_));
  NOi31      g039(.An(new_n49_), .B(new_n48_), .C(i6), .Y(new_n50_));
  OAI210     g040(.A0(new_n50_), .A1(new_n47_), .B0(i1), .Y(new_n51_));
  NOi21      g041(.An(i6), .B(i8), .Y(new_n52_));
  NOi21      g042(.An(i8), .B(i4), .Y(new_n53_));
  AOI220     g043(.A0(new_n53_), .A1(i5), .B0(new_n52_), .B1(i4), .Y(new_n54_));
  NA2        g044(.A(i7), .B(new_n30_), .Y(new_n55_));
  AOI210     g045(.A0(new_n20_), .A1(i5), .B0(new_n32_), .Y(new_n56_));
  OAI220     g046(.A0(new_n56_), .A1(new_n55_), .B0(new_n54_), .B1(i2), .Y(new_n57_));
  NA2        g047(.A(new_n17_), .B(i6), .Y(new_n58_));
  NOi21      g048(.An(i6), .B(i2), .Y(new_n59_));
  AOI220     g049(.A0(new_n59_), .A1(new_n24_), .B0(new_n53_), .B1(new_n11_), .Y(new_n60_));
  NOi21      g050(.An(i1), .B(i3), .Y(new_n61_));
  NOi21      g051(.An(i3), .B(i6), .Y(new_n62_));
  NO3        g052(.A(new_n62_), .B(new_n61_), .C(new_n12_), .Y(new_n63_));
  OAI220     g053(.A0(new_n63_), .A1(new_n60_), .B0(new_n58_), .B1(new_n49_), .Y(new_n64_));
  AOI210     g054(.A0(new_n57_), .A1(new_n34_), .B0(new_n64_), .Y(new_n65_));
  NA2        g055(.A(new_n65_), .B(new_n51_), .Y(new_n66_));
  OAI210     g056(.A0(new_n66_), .A1(new_n44_), .B0(i0), .Y(new_n67_));
  OA210      g057(.A0(new_n32_), .A1(new_n31_), .B0(new_n15_), .Y(new_n68_));
  NA2        g058(.A(new_n19_), .B(i1), .Y(new_n69_));
  OAI220     g059(.A0(new_n69_), .A1(i8), .B0(new_n26_), .B1(i0), .Y(new_n70_));
  OAI210     g060(.A0(new_n70_), .A1(new_n68_), .B0(i7), .Y(new_n71_));
  INV        g061(.A(i1), .Y(new_n72_));
  NOi21      g062(.An(i3), .B(i2), .Y(new_n73_));
  AOI220     g063(.A0(new_n73_), .A1(new_n72_), .B0(new_n31_), .B1(new_n24_), .Y(new_n74_));
  NO2        g064(.A(new_n62_), .B(new_n31_), .Y(new_n75_));
  INV        g065(.A(i0), .Y(new_n76_));
  NA2        g066(.A(i1), .B(new_n76_), .Y(new_n77_));
  OA220      g067(.A0(new_n77_), .A1(new_n75_), .B0(new_n74_), .B1(new_n20_), .Y(new_n78_));
  AOI210     g068(.A0(new_n78_), .A1(new_n71_), .B0(i5), .Y(new_n79_));
  NOi21      g069(.An(i7), .B(i8), .Y(new_n80_));
  OAI210     g070(.A0(new_n80_), .A1(new_n35_), .B0(new_n34_), .Y(new_n81_));
  OAI210     g071(.A0(new_n69_), .A1(i7), .B0(new_n81_), .Y(new_n82_));
  NA3        g072(.A(new_n52_), .B(new_n27_), .C(new_n26_), .Y(new_n83_));
  NOi21      g073(.An(i8), .B(i5), .Y(new_n84_));
  AOI220     g074(.A0(new_n84_), .A1(new_n34_), .B0(new_n12_), .B1(new_n76_), .Y(new_n85_));
  OAI210     g075(.A0(new_n85_), .A1(i1), .B0(new_n83_), .Y(new_n86_));
  AOI210     g076(.A0(new_n82_), .A1(new_n76_), .B0(new_n86_), .Y(new_n87_));
  NA3        g077(.A(new_n73_), .B(new_n19_), .C(i5), .Y(new_n88_));
  NOi31      g078(.An(i5), .B(i6), .C(i3), .Y(new_n89_));
  NOi31      g079(.An(i8), .B(i2), .C(i0), .Y(new_n90_));
  OAI210     g080(.A0(new_n90_), .A1(new_n89_), .B0(i1), .Y(new_n91_));
  AOI210     g081(.A0(new_n91_), .A1(new_n88_), .B0(i7), .Y(new_n92_));
  NA3        g082(.A(new_n32_), .B(i5), .C(new_n34_), .Y(new_n93_));
  NOi21      g083(.An(i5), .B(i3), .Y(new_n94_));
  NOi21      g084(.An(i6), .B(i1), .Y(new_n95_));
  OAI210     g085(.A0(new_n73_), .A1(new_n94_), .B0(new_n95_), .Y(new_n96_));
  AOI210     g086(.A0(new_n96_), .A1(new_n93_), .B0(i0), .Y(new_n97_));
  NOi31      g087(.An(new_n16_), .B(new_n27_), .C(i2), .Y(new_n98_));
  NO3        g088(.A(new_n98_), .B(new_n97_), .C(new_n92_), .Y(new_n99_));
  OAI210     g089(.A0(new_n87_), .A1(new_n15_), .B0(new_n99_), .Y(new_n100_));
  OAI210     g090(.A0(new_n100_), .A1(new_n79_), .B0(i4), .Y(new_n101_));
  NO3        g091(.A(i8), .B(i7), .C(new_n34_), .Y(new_n102_));
  NO2        g092(.A(new_n38_), .B(i4), .Y(new_n103_));
  OAI210     g093(.A0(new_n103_), .A1(new_n102_), .B0(i2), .Y(new_n104_));
  NO2        g094(.A(i7), .B(new_n34_), .Y(new_n105_));
  AOI220     g095(.A0(new_n84_), .A1(new_n105_), .B0(new_n28_), .B1(new_n15_), .Y(new_n106_));
  AOI210     g096(.A0(new_n106_), .A1(new_n104_), .B0(new_n72_), .Y(new_n107_));
  NA2        g097(.A(i8), .B(new_n30_), .Y(new_n108_));
  NOi21      g098(.An(i6), .B(i4), .Y(new_n109_));
  OAI210     g099(.A0(new_n109_), .A1(new_n73_), .B0(new_n11_), .Y(new_n110_));
  OAI210     g100(.A0(new_n108_), .A1(i6), .B0(new_n110_), .Y(new_n111_));
  AOI220     g101(.A0(new_n84_), .A1(new_n72_), .B0(new_n52_), .B1(new_n30_), .Y(new_n112_));
  NO2        g102(.A(new_n109_), .B(new_n32_), .Y(new_n113_));
  NA2        g103(.A(i5), .B(new_n72_), .Y(new_n114_));
  OAI220     g104(.A0(new_n114_), .A1(new_n113_), .B0(new_n112_), .B1(new_n15_), .Y(new_n115_));
  AOI210     g105(.A0(new_n111_), .A1(i1), .B0(new_n115_), .Y(new_n116_));
  NO2        g106(.A(new_n109_), .B(new_n16_), .Y(new_n117_));
  NA2        g107(.A(i2), .B(new_n72_), .Y(new_n118_));
  OA220      g108(.A0(new_n118_), .A1(i7), .B0(new_n117_), .B1(i2), .Y(new_n119_));
  NOi21      g109(.An(new_n21_), .B(new_n35_), .Y(new_n120_));
  OA220      g110(.A0(new_n120_), .A1(new_n117_), .B0(new_n119_), .B1(new_n20_), .Y(new_n121_));
  OAI220     g111(.A0(new_n121_), .A1(new_n34_), .B0(new_n116_), .B1(new_n24_), .Y(new_n122_));
  OAI210     g112(.A0(new_n122_), .A1(new_n107_), .B0(new_n76_), .Y(new_n123_));
  AOI220     g113(.A0(new_n118_), .A1(new_n69_), .B0(new_n108_), .B1(new_n26_), .Y(new_n124_));
  NA2        g114(.A(new_n20_), .B(i2), .Y(new_n125_));
  AOI210     g115(.A0(new_n108_), .A1(new_n125_), .B0(new_n38_), .Y(new_n126_));
  OAI210     g116(.A0(new_n126_), .A1(new_n124_), .B0(new_n24_), .Y(new_n127_));
  NA3        g117(.A(new_n53_), .B(i3), .C(new_n72_), .Y(new_n128_));
  OAI210     g118(.A0(new_n69_), .A1(new_n39_), .B0(new_n128_), .Y(new_n129_));
  OAI220     g119(.A0(new_n24_), .A1(i3), .B0(i4), .B1(new_n72_), .Y(new_n130_));
  NO2        g120(.A(new_n125_), .B(i6), .Y(new_n131_));
  AOI220     g121(.A0(new_n131_), .A1(new_n130_), .B0(new_n129_), .B1(new_n15_), .Y(new_n132_));
  AOI210     g122(.A0(new_n132_), .A1(new_n127_), .B0(new_n11_), .Y(new_n133_));
  INV        g123(.A(new_n59_), .Y(new_n134_));
  NA3        g124(.A(i7), .B(new_n30_), .C(i3), .Y(new_n135_));
  AOI210     g125(.A0(new_n135_), .A1(new_n58_), .B0(i1), .Y(new_n136_));
  NOi21      g126(.An(new_n102_), .B(new_n19_), .Y(new_n137_));
  NA2        g127(.A(new_n32_), .B(new_n34_), .Y(new_n138_));
  NA2        g128(.A(new_n80_), .B(new_n30_), .Y(new_n139_));
  AOI210     g129(.A0(new_n139_), .A1(new_n138_), .B0(new_n72_), .Y(new_n140_));
  NO3        g130(.A(new_n140_), .B(new_n137_), .C(new_n136_), .Y(new_n141_));
  AOI210     g131(.A0(new_n108_), .A1(new_n26_), .B0(new_n24_), .Y(new_n142_));
  AOI210     g132(.A0(new_n17_), .A1(i1), .B0(new_n142_), .Y(new_n143_));
  OAI220     g133(.A0(new_n143_), .A1(new_n134_), .B0(new_n141_), .B1(new_n15_), .Y(new_n144_));
  AOI210     g134(.A0(new_n144_), .A1(new_n11_), .B0(new_n133_), .Y(new_n145_));
  NA4        g135(.A(new_n145_), .B(new_n123_), .C(new_n101_), .D(new_n67_), .Y(zori0));
endmodule


