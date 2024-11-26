// Benchmark "9sym/9sym_1_21_0_esp" written by ABC on Wed Mar  8 02:21:10 2023

module \9sym/9sym_1_21_0_esp  ( 
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
    new_n102_;
  INV        g00(.A(i1), .Y(new_n11_));
  INV        g01(.A(i0), .Y(new_n12_));
  NOi21      g02(.An(i8), .B(i6), .Y(new_n13_));
  INV        g03(.A(i4), .Y(new_n14_));
  NO3        g04(.A(i8), .B(new_n14_), .C(i3), .Y(new_n15_));
  OA210      g05(.A0(new_n15_), .A1(new_n13_), .B0(i5), .Y(new_n16_));
  NOi21      g06(.An(i3), .B(i7), .Y(new_n17_));
  NOi21      g07(.An(i8), .B(i3), .Y(new_n18_));
  AOI210     g08(.A0(new_n18_), .A1(i4), .B0(new_n17_), .Y(new_n19_));
  INV        g09(.A(i7), .Y(new_n20_));
  NOi21      g10(.An(i6), .B(i8), .Y(new_n21_));
  NOi21      g11(.An(i3), .B(i5), .Y(new_n22_));
  AOI210     g12(.A0(new_n21_), .A1(new_n20_), .B0(new_n22_), .Y(new_n23_));
  OAI210     g13(.A0(new_n19_), .A1(i2), .B0(new_n23_), .Y(new_n24_));
  OAI210     g14(.A0(new_n24_), .A1(new_n16_), .B0(new_n12_), .Y(new_n25_));
  INV        g15(.A(i2), .Y(new_n26_));
  INV        g16(.A(i3), .Y(new_n27_));
  NOi21      g17(.An(i4), .B(i6), .Y(new_n28_));
  AOI210     g18(.A0(i7), .A1(new_n27_), .B0(new_n28_), .Y(new_n29_));
  INV        g19(.A(i8), .Y(new_n30_));
  NA2        g20(.A(new_n30_), .B(i0), .Y(new_n31_));
  NOi31      g21(.An(i8), .B(i5), .C(i3), .Y(new_n32_));
  AOI220     g22(.A0(new_n32_), .A1(i7), .B0(i6), .B1(new_n14_), .Y(new_n33_));
  OAI210     g23(.A0(new_n31_), .A1(new_n29_), .B0(new_n33_), .Y(new_n34_));
  INV        g24(.A(i5), .Y(new_n35_));
  NOi21      g25(.An(i7), .B(i8), .Y(new_n36_));
  NOi41      g26(.An(i5), .B(i4), .C(i3), .D(i0), .Y(new_n37_));
  AOI210     g27(.A0(new_n36_), .A1(new_n35_), .B0(new_n37_), .Y(new_n38_));
  NA2        g28(.A(new_n27_), .B(i0), .Y(new_n39_));
  NA2        g29(.A(new_n20_), .B(i5), .Y(new_n40_));
  OAI220     g30(.A0(new_n40_), .A1(new_n39_), .B0(new_n38_), .B1(new_n26_), .Y(new_n41_));
  AOI210     g31(.A0(new_n34_), .A1(new_n26_), .B0(new_n41_), .Y(new_n42_));
  AOI210     g32(.A0(new_n42_), .A1(new_n25_), .B0(new_n11_), .Y(new_n43_));
  NA2        g33(.A(i8), .B(new_n20_), .Y(new_n44_));
  NA3        g34(.A(i7), .B(i3), .C(new_n11_), .Y(new_n45_));
  AOI210     g35(.A0(new_n45_), .A1(new_n44_), .B0(i0), .Y(new_n46_));
  OAI210     g36(.A0(new_n46_), .A1(new_n32_), .B0(i2), .Y(new_n47_));
  INV        g37(.A(i6), .Y(new_n48_));
  NO2        g38(.A(new_n20_), .B(new_n48_), .Y(new_n49_));
  AO220      g39(.A0(new_n49_), .A1(new_n22_), .B0(new_n13_), .B1(i0), .Y(new_n50_));
  NOi21      g40(.An(i8), .B(i7), .Y(new_n51_));
  NO2        g41(.A(new_n51_), .B(i6), .Y(new_n52_));
  AN2        g42(.A(i8), .B(i6), .Y(new_n53_));
  OR2        g43(.A(new_n53_), .B(new_n12_), .Y(new_n54_));
  NA3        g44(.A(new_n51_), .B(new_n22_), .C(i6), .Y(new_n55_));
  OAI210     g45(.A0(new_n54_), .A1(new_n52_), .B0(new_n55_), .Y(new_n56_));
  AOI210     g46(.A0(new_n50_), .A1(new_n11_), .B0(new_n56_), .Y(new_n57_));
  AOI210     g47(.A0(new_n57_), .A1(new_n47_), .B0(i4), .Y(new_n58_));
  NA2        g48(.A(new_n30_), .B(i7), .Y(new_n59_));
  NA3        g49(.A(i8), .B(i3), .C(new_n11_), .Y(new_n60_));
  AOI210     g50(.A0(new_n60_), .A1(new_n59_), .B0(i0), .Y(new_n61_));
  OAI210     g51(.A0(new_n53_), .A1(i7), .B0(new_n14_), .Y(new_n62_));
  NA2        g52(.A(new_n21_), .B(new_n17_), .Y(new_n63_));
  NA3        g53(.A(new_n28_), .B(new_n11_), .C(i0), .Y(new_n64_));
  NA3        g54(.A(new_n64_), .B(new_n63_), .C(new_n62_), .Y(new_n65_));
  OAI210     g55(.A0(new_n65_), .A1(new_n61_), .B0(new_n26_), .Y(new_n66_));
  NA2        g56(.A(i7), .B(new_n11_), .Y(new_n67_));
  OAI210     g57(.A0(new_n13_), .A1(new_n14_), .B0(new_n27_), .Y(new_n68_));
  AOI210     g58(.A0(new_n67_), .A1(new_n14_), .B0(new_n68_), .Y(new_n69_));
  NO2        g59(.A(i3), .B(i0), .Y(new_n70_));
  NOi21      g60(.An(i6), .B(i1), .Y(new_n71_));
  NO2        g61(.A(i8), .B(i4), .Y(new_n72_));
  AOI220     g62(.A0(new_n72_), .A1(i3), .B0(new_n71_), .B1(new_n70_), .Y(new_n73_));
  NA3        g63(.A(new_n28_), .B(new_n17_), .C(new_n30_), .Y(new_n74_));
  OAI210     g64(.A0(new_n73_), .A1(new_n26_), .B0(new_n74_), .Y(new_n75_));
  NO2        g65(.A(new_n75_), .B(new_n69_), .Y(new_n76_));
  AOI210     g66(.A0(new_n76_), .A1(new_n66_), .B0(new_n35_), .Y(new_n77_));
  NO3        g67(.A(new_n20_), .B(new_n48_), .C(i5), .Y(new_n78_));
  OA210      g68(.A0(new_n78_), .A1(new_n28_), .B0(new_n12_), .Y(new_n79_));
  NA2        g69(.A(new_n71_), .B(new_n17_), .Y(new_n80_));
  OAI210     g70(.A0(new_n39_), .A1(i6), .B0(new_n80_), .Y(new_n81_));
  OAI210     g71(.A0(new_n81_), .A1(new_n79_), .B0(i2), .Y(new_n82_));
  AO210      g72(.A0(new_n22_), .A1(new_n26_), .B0(new_n30_), .Y(new_n83_));
  NO2        g73(.A(new_n72_), .B(new_n67_), .Y(new_n84_));
  NA3        g74(.A(new_n17_), .B(i8), .C(new_n26_), .Y(new_n85_));
  NAi21      g75(.An(new_n21_), .B(new_n85_), .Y(new_n86_));
  NO2        g76(.A(i5), .B(new_n14_), .Y(new_n87_));
  AOI220     g77(.A0(new_n87_), .A1(new_n86_), .B0(new_n84_), .B1(new_n83_), .Y(new_n88_));
  NA2        g78(.A(i8), .B(new_n11_), .Y(new_n89_));
  NA2        g79(.A(new_n35_), .B(i0), .Y(new_n90_));
  NA3        g80(.A(i7), .B(i6), .C(new_n26_), .Y(new_n91_));
  AOI210     g81(.A0(new_n91_), .A1(new_n90_), .B0(new_n89_), .Y(new_n92_));
  NO2        g82(.A(i7), .B(i6), .Y(new_n93_));
  NO3        g83(.A(new_n93_), .B(new_n49_), .C(new_n14_), .Y(new_n94_));
  OAI210     g84(.A0(new_n94_), .A1(new_n92_), .B0(new_n27_), .Y(new_n95_));
  NO2        g85(.A(i8), .B(i5), .Y(new_n96_));
  NO3        g86(.A(i7), .B(i6), .C(i2), .Y(new_n97_));
  OAI210     g87(.A0(new_n97_), .A1(new_n96_), .B0(i3), .Y(new_n98_));
  OAI220     g88(.A0(i7), .A1(i1), .B0(i6), .B1(i5), .Y(new_n99_));
  NA2        g89(.A(new_n99_), .B(i8), .Y(new_n100_));
  AO210      g90(.A0(new_n100_), .A1(new_n98_), .B0(new_n12_), .Y(new_n101_));
  NA4        g91(.A(new_n101_), .B(new_n95_), .C(new_n88_), .D(new_n82_), .Y(new_n102_));
  OR4        g92(.A(new_n102_), .B(new_n77_), .C(new_n58_), .D(new_n43_), .Y(zori0));
endmodule


