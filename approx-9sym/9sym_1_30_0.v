// Benchmark "9sym/9sym_1_30_0_esp" written by ABC on Wed Mar  8 02:23:54 2023

module \9sym/9sym_1_30_0_esp  ( 
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
    new_n74_, new_n75_, new_n76_, new_n77_, new_n78_, new_n79_;
  OAI210     g00(.A0(i5), .A1(i3), .B0(i0), .Y(new_n11_));
  NA2        g01(.A(new_n11_), .B(i2), .Y(new_n12_));
  INV        g02(.A(i7), .Y(new_n13_));
  INV        g03(.A(i6), .Y(new_n14_));
  NAi21      g04(.An(i5), .B(i3), .Y(new_n15_));
  NAi21      g05(.An(i6), .B(i0), .Y(new_n16_));
  OAI210     g06(.A0(new_n15_), .A1(new_n14_), .B0(new_n16_), .Y(new_n17_));
  NA2        g07(.A(new_n17_), .B(new_n13_), .Y(new_n18_));
  AOI210     g08(.A0(new_n18_), .A1(new_n12_), .B0(i4), .Y(new_n19_));
  INV        g09(.A(i2), .Y(new_n20_));
  OAI210     g10(.A0(i6), .A1(i2), .B0(i7), .Y(new_n21_));
  NA2        g11(.A(i5), .B(i3), .Y(new_n22_));
  NAi31      g12(.An(i3), .B(i7), .C(i6), .Y(new_n23_));
  OAI210     g13(.A0(new_n22_), .A1(i0), .B0(new_n23_), .Y(new_n24_));
  AOI220     g14(.A0(new_n24_), .A1(new_n20_), .B0(new_n21_), .B1(i0), .Y(new_n25_));
  INV        g15(.A(i1), .Y(new_n26_));
  OR2        g16(.A(i3), .B(i2), .Y(new_n27_));
  NAi21      g17(.An(i5), .B(i7), .Y(new_n28_));
  NAi21      g18(.An(i0), .B(i5), .Y(new_n29_));
  OA220      g19(.A0(new_n29_), .A1(i6), .B0(new_n28_), .B1(new_n27_), .Y(new_n30_));
  INV        g20(.A(i0), .Y(new_n31_));
  INV        g21(.A(i3), .Y(new_n32_));
  NO2        g22(.A(i7), .B(i5), .Y(new_n33_));
  AOI210     g23(.A0(new_n32_), .A1(new_n31_), .B0(new_n33_), .Y(new_n34_));
  NAi21      g24(.An(i2), .B(i4), .Y(new_n35_));
  OA220      g25(.A0(new_n35_), .A1(new_n34_), .B0(new_n30_), .B1(new_n26_), .Y(new_n36_));
  OAI210     g26(.A0(new_n25_), .A1(i1), .B0(new_n36_), .Y(new_n37_));
  OAI210     g27(.A0(new_n37_), .A1(new_n19_), .B0(i8), .Y(new_n38_));
  NO3        g28(.A(i7), .B(new_n32_), .C(i1), .Y(new_n39_));
  AN2        g29(.A(i5), .B(i3), .Y(new_n40_));
  NA2        g30(.A(i5), .B(i1), .Y(new_n41_));
  NOi41      g31(.An(new_n41_), .B(new_n33_), .C(new_n40_), .D(i0), .Y(new_n42_));
  OAI210     g32(.A0(new_n42_), .A1(new_n39_), .B0(i2), .Y(new_n43_));
  INV        g33(.A(i4), .Y(new_n44_));
  NA2        g34(.A(new_n44_), .B(new_n20_), .Y(new_n45_));
  INV        g35(.A(i8), .Y(new_n46_));
  NA3        g36(.A(new_n46_), .B(new_n13_), .C(new_n31_), .Y(new_n47_));
  AO210      g37(.A0(new_n47_), .A1(new_n45_), .B0(new_n26_), .Y(new_n48_));
  NO2        g38(.A(i7), .B(i2), .Y(new_n49_));
  AO220      g39(.A0(new_n49_), .A1(new_n40_), .B0(new_n44_), .B1(i0), .Y(new_n50_));
  OAI220     g40(.A0(i8), .A1(i5), .B0(i7), .B1(i3), .Y(new_n51_));
  AOI220     g41(.A0(new_n51_), .A1(i4), .B0(new_n50_), .B1(new_n46_), .Y(new_n52_));
  NA3        g42(.A(new_n52_), .B(new_n48_), .C(new_n43_), .Y(new_n53_));
  NA2        g43(.A(i4), .B(i2), .Y(new_n54_));
  OAI220     g44(.A0(new_n54_), .A1(i6), .B0(new_n15_), .B1(new_n26_), .Y(new_n55_));
  NA2        g45(.A(new_n13_), .B(new_n32_), .Y(new_n56_));
  NO2        g46(.A(new_n49_), .B(new_n22_), .Y(new_n57_));
  NA2        g47(.A(new_n27_), .B(new_n14_), .Y(new_n58_));
  OAI220     g48(.A0(new_n58_), .A1(new_n57_), .B0(new_n56_), .B1(new_n41_), .Y(new_n59_));
  MUX2       g49(.S(new_n31_), .A(new_n59_), .B(new_n55_), .Y(new_n60_));
  AOI210     g50(.A0(new_n53_), .A1(i6), .B0(new_n60_), .Y(new_n61_));
  INV        g51(.A(i5), .Y(new_n62_));
  NAi21      g52(.An(i3), .B(i4), .Y(new_n63_));
  OAI220     g53(.A0(new_n63_), .A1(new_n62_), .B0(new_n32_), .B1(i2), .Y(new_n64_));
  NAi21      g54(.An(i4), .B(i5), .Y(new_n65_));
  OAI220     g55(.A0(new_n65_), .A1(new_n20_), .B0(new_n35_), .B1(new_n16_), .Y(new_n66_));
  AOI210     g56(.A0(new_n64_), .A1(new_n31_), .B0(new_n66_), .Y(new_n67_));
  OAI210     g57(.A0(i7), .A1(i6), .B0(i4), .Y(new_n68_));
  NA3        g58(.A(new_n68_), .B(new_n45_), .C(new_n40_), .Y(new_n69_));
  OAI210     g59(.A0(new_n67_), .A1(new_n26_), .B0(new_n69_), .Y(new_n70_));
  OAI220     g60(.A0(new_n27_), .A1(new_n31_), .B0(i5), .B1(new_n20_), .Y(new_n71_));
  OAI210     g61(.A0(new_n44_), .A1(i1), .B0(new_n15_), .Y(new_n72_));
  AOI210     g62(.A0(new_n71_), .A1(i1), .B0(new_n72_), .Y(new_n73_));
  OAI210     g63(.A0(new_n32_), .A1(i2), .B0(new_n65_), .Y(new_n74_));
  NO2        g64(.A(new_n40_), .B(i1), .Y(new_n75_));
  OAI220     g65(.A0(new_n65_), .A1(i2), .B0(new_n63_), .B1(i6), .Y(new_n76_));
  AOI210     g66(.A0(new_n75_), .A1(new_n74_), .B0(new_n76_), .Y(new_n77_));
  OAI210     g67(.A0(new_n73_), .A1(i8), .B0(new_n77_), .Y(new_n78_));
  AOI220     g68(.A0(new_n78_), .A1(i7), .B0(new_n70_), .B1(new_n46_), .Y(new_n79_));
  NA3        g69(.A(new_n79_), .B(new_n61_), .C(new_n38_), .Y(zori0));
endmodule


