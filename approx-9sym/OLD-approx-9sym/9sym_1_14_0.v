// Benchmark "9sym/9sym_1_14_0_esp" written by ABC on Wed Mar  8 02:19:21 2023

module \9sym/9sym_1_14_0_esp  ( 
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
    new_n108_, new_n109_;
  INV        g00(.A(i8), .Y(new_n11_));
  NO2        g01(.A(new_n11_), .B(i2), .Y(new_n12_));
  NA2        g02(.A(new_n12_), .B(i5), .Y(new_n13_));
  NOi21      g03(.An(i7), .B(i5), .Y(new_n14_));
  NO2        g04(.A(new_n14_), .B(i3), .Y(new_n15_));
  INV        g05(.A(i3), .Y(new_n16_));
  INV        g06(.A(i0), .Y(new_n17_));
  NO3        g07(.A(i5), .B(i2), .C(new_n17_), .Y(new_n18_));
  NAi21      g08(.An(i7), .B(i5), .Y(new_n19_));
  OR2        g09(.A(new_n19_), .B(i8), .Y(new_n20_));
  NOi31      g10(.An(new_n20_), .B(new_n18_), .C(new_n16_), .Y(new_n21_));
  AOI210     g11(.A0(new_n15_), .A1(new_n13_), .B0(new_n21_), .Y(new_n22_));
  INV        g12(.A(i7), .Y(new_n23_));
  NO3        g13(.A(i8), .B(new_n23_), .C(i1), .Y(new_n24_));
  OAI210     g14(.A0(new_n24_), .A1(new_n22_), .B0(i4), .Y(new_n25_));
  NO3        g15(.A(new_n11_), .B(i7), .C(i5), .Y(new_n26_));
  INV        g16(.A(i1), .Y(new_n27_));
  NO3        g17(.A(i8), .B(i2), .C(new_n27_), .Y(new_n28_));
  OAI210     g18(.A0(new_n28_), .A1(new_n26_), .B0(i4), .Y(new_n29_));
  NAi21      g19(.An(i4), .B(i7), .Y(new_n30_));
  OAI220     g20(.A0(new_n30_), .A1(i1), .B0(new_n19_), .B1(i2), .Y(new_n31_));
  OAI220     g21(.A0(i8), .A1(i3), .B0(i5), .B1(i1), .Y(new_n32_));
  AOI220     g22(.A0(new_n32_), .A1(i2), .B0(new_n31_), .B1(i3), .Y(new_n33_));
  AOI210     g23(.A0(new_n33_), .A1(new_n29_), .B0(new_n17_), .Y(new_n34_));
  INV        g24(.A(i2), .Y(new_n35_));
  INV        g25(.A(i4), .Y(new_n36_));
  AO220      g26(.A0(new_n14_), .A1(new_n36_), .B0(i5), .B1(new_n17_), .Y(new_n37_));
  NAi21      g27(.An(i2), .B(i7), .Y(new_n38_));
  NAi21      g28(.An(i7), .B(i3), .Y(new_n39_));
  OAI220     g29(.A0(new_n39_), .A1(i4), .B0(new_n38_), .B1(i1), .Y(new_n40_));
  AOI220     g30(.A0(new_n40_), .A1(i0), .B0(new_n37_), .B1(i1), .Y(new_n41_));
  NA2        g31(.A(i8), .B(i3), .Y(new_n42_));
  AOI210     g32(.A0(new_n42_), .A1(new_n36_), .B0(i0), .Y(new_n43_));
  NA2        g33(.A(new_n30_), .B(i1), .Y(new_n44_));
  AN2        g34(.A(i8), .B(i5), .Y(new_n45_));
  OA210      g35(.A0(new_n45_), .A1(i1), .B0(new_n16_), .Y(new_n46_));
  AOI210     g36(.A0(new_n46_), .A1(new_n44_), .B0(new_n43_), .Y(new_n47_));
  OAI220     g37(.A0(new_n47_), .A1(new_n35_), .B0(new_n41_), .B1(new_n11_), .Y(new_n48_));
  NO2        g38(.A(new_n48_), .B(new_n34_), .Y(new_n49_));
  AO210      g39(.A0(new_n49_), .A1(new_n25_), .B0(i6), .Y(new_n50_));
  INV        g40(.A(i5), .Y(new_n51_));
  INV        g41(.A(i6), .Y(new_n52_));
  NO3        g42(.A(new_n52_), .B(i3), .C(new_n35_), .Y(new_n53_));
  NO3        g43(.A(new_n11_), .B(new_n16_), .C(i2), .Y(new_n54_));
  OAI210     g44(.A0(new_n54_), .A1(new_n53_), .B0(new_n17_), .Y(new_n55_));
  NO2        g45(.A(i4), .B(i3), .Y(new_n56_));
  NA2        g46(.A(i8), .B(new_n23_), .Y(new_n57_));
  NA2        g47(.A(new_n11_), .B(i4), .Y(new_n58_));
  OAI210     g48(.A0(new_n58_), .A1(i2), .B0(new_n57_), .Y(new_n59_));
  AOI220     g49(.A0(new_n59_), .A1(i0), .B0(new_n56_), .B1(i7), .Y(new_n60_));
  AOI210     g50(.A0(new_n60_), .A1(new_n55_), .B0(new_n51_), .Y(new_n61_));
  NO3        g51(.A(new_n38_), .B(new_n52_), .C(i3), .Y(new_n62_));
  AOI210     g52(.A0(i7), .A1(new_n16_), .B0(new_n35_), .Y(new_n63_));
  OAI210     g53(.A0(new_n52_), .A1(i4), .B0(new_n63_), .Y(new_n64_));
  NA2        g54(.A(i7), .B(i3), .Y(new_n65_));
  AOI210     g55(.A0(new_n65_), .A1(new_n35_), .B0(i5), .Y(new_n66_));
  AOI210     g56(.A0(new_n66_), .A1(new_n64_), .B0(new_n62_), .Y(new_n67_));
  NA2        g57(.A(new_n36_), .B(i2), .Y(new_n68_));
  OAI220     g58(.A0(new_n68_), .A1(new_n65_), .B0(new_n58_), .B1(new_n52_), .Y(new_n69_));
  OAI220     g59(.A0(new_n58_), .A1(i3), .B0(new_n39_), .B1(new_n52_), .Y(new_n70_));
  AOI220     g60(.A0(new_n70_), .A1(i2), .B0(new_n69_), .B1(new_n17_), .Y(new_n71_));
  OAI210     g61(.A0(new_n67_), .A1(new_n11_), .B0(new_n71_), .Y(new_n72_));
  OAI210     g62(.A0(new_n72_), .A1(new_n61_), .B0(new_n27_), .Y(new_n73_));
  NA2        g63(.A(i5), .B(i1), .Y(new_n74_));
  AOI210     g64(.A0(new_n74_), .A1(new_n65_), .B0(i4), .Y(new_n75_));
  NA3        g65(.A(new_n14_), .B(new_n16_), .C(i1), .Y(new_n76_));
  OAI210     g66(.A0(new_n20_), .A1(new_n16_), .B0(new_n76_), .Y(new_n77_));
  OAI210     g67(.A0(new_n77_), .A1(new_n75_), .B0(new_n35_), .Y(new_n78_));
  OAI210     g68(.A0(i5), .A1(i3), .B0(i8), .Y(new_n79_));
  NA2        g69(.A(new_n79_), .B(i0), .Y(new_n80_));
  NA4        g70(.A(i8), .B(new_n23_), .C(new_n51_), .D(i3), .Y(new_n81_));
  AOI210     g71(.A0(new_n81_), .A1(new_n80_), .B0(i4), .Y(new_n82_));
  XO2        g72(.A(i8), .B(i2), .Y(new_n83_));
  NOi21      g73(.An(i1), .B(i7), .Y(new_n84_));
  AOI220     g74(.A0(new_n84_), .A1(new_n83_), .B0(new_n14_), .B1(i2), .Y(new_n85_));
  NO2        g75(.A(i7), .B(i3), .Y(new_n86_));
  NO2        g76(.A(i8), .B(i5), .Y(new_n87_));
  OAI220     g77(.A0(new_n87_), .A1(new_n86_), .B0(new_n45_), .B1(i4), .Y(new_n88_));
  OAI210     g78(.A0(new_n85_), .A1(i0), .B0(new_n88_), .Y(new_n89_));
  NO2        g79(.A(new_n89_), .B(new_n82_), .Y(new_n90_));
  AOI210     g80(.A0(new_n90_), .A1(new_n78_), .B0(new_n52_), .Y(new_n91_));
  NA2        g81(.A(i5), .B(new_n17_), .Y(new_n92_));
  AOI210     g82(.A0(new_n68_), .A1(new_n58_), .B0(new_n92_), .Y(new_n93_));
  NO3        g83(.A(new_n87_), .B(i7), .C(new_n17_), .Y(new_n94_));
  OAI210     g84(.A0(new_n94_), .A1(new_n93_), .B0(new_n16_), .Y(new_n95_));
  NA2        g85(.A(new_n51_), .B(i3), .Y(new_n96_));
  OAI210     g86(.A0(new_n68_), .A1(new_n57_), .B0(new_n96_), .Y(new_n97_));
  OAI210     g87(.A0(new_n96_), .A1(i8), .B0(i0), .Y(new_n98_));
  NA2        g88(.A(new_n98_), .B(new_n97_), .Y(new_n99_));
  AOI210     g89(.A0(new_n99_), .A1(new_n95_), .B0(new_n27_), .Y(new_n100_));
  NO2        g90(.A(i8), .B(i3), .Y(new_n101_));
  NOi41      g91(.An(new_n42_), .B(new_n56_), .C(new_n101_), .D(i0), .Y(new_n102_));
  NOi31      g92(.An(new_n101_), .B(new_n23_), .C(new_n17_), .Y(new_n103_));
  OAI210     g93(.A0(new_n103_), .A1(new_n102_), .B0(i1), .Y(new_n104_));
  OA220      g94(.A0(new_n81_), .A1(new_n36_), .B0(new_n30_), .B1(new_n51_), .Y(new_n105_));
  AOI210     g95(.A0(new_n105_), .A1(new_n104_), .B0(i2), .Y(new_n106_));
  NA3        g96(.A(i5), .B(new_n36_), .C(i3), .Y(new_n107_));
  NO3        g97(.A(new_n107_), .B(i8), .C(new_n35_), .Y(new_n108_));
  NO4        g98(.A(new_n108_), .B(new_n106_), .C(new_n100_), .D(new_n91_), .Y(new_n109_));
  NA3        g99(.A(new_n109_), .B(new_n73_), .C(new_n50_), .Y(zori0));
endmodule


