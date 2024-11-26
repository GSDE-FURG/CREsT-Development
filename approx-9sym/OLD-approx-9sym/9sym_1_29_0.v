// Benchmark "9sym/9sym_1_29_0_esp" written by ABC on Wed Mar  8 02:23:41 2023

module \9sym/9sym_1_29_0_esp  ( 
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
    new_n81_;
  INV        g00(.A(i3), .Y(new_n11_));
  NAi21      g01(.An(i5), .B(i2), .Y(new_n12_));
  NAi31      g02(.An(i2), .B(i7), .C(i6), .Y(new_n13_));
  AOI210     g03(.A0(new_n13_), .A1(new_n12_), .B0(i1), .Y(new_n14_));
  NO2        g04(.A(new_n12_), .B(i4), .Y(new_n15_));
  OA210      g05(.A0(new_n15_), .A1(new_n14_), .B0(new_n11_), .Y(new_n16_));
  OR2        g06(.A(i6), .B(i2), .Y(new_n17_));
  AOI210     g07(.A0(new_n17_), .A1(i7), .B0(i1), .Y(new_n18_));
  NO3        g08(.A(i7), .B(i6), .C(i4), .Y(new_n19_));
  OAI210     g09(.A0(new_n19_), .A1(new_n18_), .B0(i0), .Y(new_n20_));
  NOi41      g10(.An(i5), .B(i2), .C(i1), .D(i0), .Y(new_n21_));
  NOi41      g11(.An(i6), .B(i7), .C(i5), .D(i4), .Y(new_n22_));
  OAI210     g12(.A0(new_n22_), .A1(new_n21_), .B0(i3), .Y(new_n23_));
  NOi31      g13(.An(i5), .B(i6), .C(i0), .Y(new_n24_));
  NOi41      g14(.An(i7), .B(i5), .C(i3), .D(i2), .Y(new_n25_));
  OAI210     g15(.A0(new_n25_), .A1(new_n24_), .B0(i1), .Y(new_n26_));
  INV        g16(.A(i0), .Y(new_n27_));
  OAI210     g17(.A0(i3), .A1(i2), .B0(i4), .Y(new_n28_));
  NO2        g18(.A(i4), .B(i2), .Y(new_n29_));
  NAi31      g19(.An(new_n29_), .B(new_n28_), .C(new_n27_), .Y(new_n30_));
  INV        g20(.A(i4), .Y(new_n31_));
  OR2        g21(.A(i7), .B(i2), .Y(new_n32_));
  OR3        g22(.A(new_n32_), .B(i5), .C(new_n31_), .Y(new_n33_));
  AN4        g23(.A(new_n33_), .B(new_n30_), .C(new_n26_), .D(new_n23_), .Y(new_n34_));
  NA2        g24(.A(new_n34_), .B(new_n20_), .Y(new_n35_));
  OAI210     g25(.A0(new_n35_), .A1(new_n16_), .B0(i8), .Y(new_n36_));
  NAi21      g26(.An(i5), .B(i7), .Y(new_n37_));
  NAi32      g27(.An(i3), .Bn(i1), .C(i5), .Y(new_n38_));
  AOI210     g28(.A0(new_n38_), .A1(new_n37_), .B0(i0), .Y(new_n39_));
  NO3        g29(.A(i7), .B(new_n11_), .C(i1), .Y(new_n40_));
  OAI210     g30(.A0(new_n40_), .A1(new_n39_), .B0(i2), .Y(new_n41_));
  NO3        g31(.A(i8), .B(i7), .C(i0), .Y(new_n42_));
  OAI210     g32(.A0(new_n42_), .A1(new_n29_), .B0(i1), .Y(new_n43_));
  INV        g33(.A(i8), .Y(new_n44_));
  NA2        g34(.A(i5), .B(i3), .Y(new_n45_));
  OAI220     g35(.A0(new_n45_), .A1(new_n32_), .B0(i4), .B1(new_n27_), .Y(new_n46_));
  OAI220     g36(.A0(i8), .A1(i5), .B0(i7), .B1(i3), .Y(new_n47_));
  AOI220     g37(.A0(new_n47_), .A1(i4), .B0(new_n46_), .B1(new_n44_), .Y(new_n48_));
  NA3        g38(.A(new_n48_), .B(new_n43_), .C(new_n41_), .Y(new_n49_));
  NA2        g39(.A(new_n49_), .B(i6), .Y(new_n50_));
  INV        g40(.A(i1), .Y(new_n51_));
  NO3        g41(.A(i5), .B(new_n11_), .C(new_n51_), .Y(new_n52_));
  NO2        g42(.A(i6), .B(new_n31_), .Y(new_n53_));
  AOI210     g43(.A0(new_n53_), .A1(i2), .B0(new_n52_), .Y(new_n54_));
  INV        g44(.A(i6), .Y(new_n55_));
  OR2        g45(.A(i3), .B(i2), .Y(new_n56_));
  NA2        g46(.A(new_n45_), .B(new_n56_), .Y(new_n57_));
  OAI210     g47(.A0(new_n45_), .A1(new_n32_), .B0(new_n57_), .Y(new_n58_));
  INV        g48(.A(i5), .Y(new_n59_));
  NO4        g49(.A(i7), .B(new_n59_), .C(i3), .D(new_n51_), .Y(new_n60_));
  AOI210     g50(.A0(new_n58_), .A1(new_n55_), .B0(new_n60_), .Y(new_n61_));
  MUX2       g51(.S(new_n27_), .A(new_n61_), .B(new_n54_), .Y(new_n62_));
  OAI210     g52(.A0(new_n56_), .A1(new_n27_), .B0(new_n12_), .Y(new_n63_));
  OAI220     g53(.A0(i5), .A1(new_n11_), .B0(new_n31_), .B1(i1), .Y(new_n64_));
  AOI210     g54(.A0(new_n63_), .A1(i1), .B0(new_n64_), .Y(new_n65_));
  NAi21      g55(.An(i4), .B(i5), .Y(new_n66_));
  OAI210     g56(.A0(new_n11_), .A1(i2), .B0(new_n66_), .Y(new_n67_));
  AOI210     g57(.A0(i5), .A1(i3), .B0(i1), .Y(new_n68_));
  NAi21      g58(.An(i3), .B(i4), .Y(new_n69_));
  OAI220     g59(.A0(new_n69_), .A1(i6), .B0(new_n66_), .B1(i2), .Y(new_n70_));
  AOI210     g60(.A0(new_n68_), .A1(new_n67_), .B0(new_n70_), .Y(new_n71_));
  OAI210     g61(.A0(new_n65_), .A1(i8), .B0(new_n71_), .Y(new_n72_));
  OAI220     g62(.A0(new_n69_), .A1(new_n59_), .B0(new_n11_), .B1(i2), .Y(new_n73_));
  NAi21      g63(.An(i4), .B(i2), .Y(new_n74_));
  NA2        g64(.A(i4), .B(i0), .Y(new_n75_));
  OAI220     g65(.A0(new_n75_), .A1(new_n17_), .B0(new_n74_), .B1(new_n59_), .Y(new_n76_));
  AOI210     g66(.A0(new_n73_), .A1(new_n27_), .B0(new_n76_), .Y(new_n77_));
  INV        g67(.A(i7), .Y(new_n78_));
  AOI220     g68(.A0(new_n53_), .A1(new_n78_), .B0(new_n31_), .B1(i2), .Y(new_n79_));
  OAI220     g69(.A0(new_n79_), .A1(new_n45_), .B0(new_n77_), .B1(new_n51_), .Y(new_n80_));
  AOI220     g70(.A0(new_n80_), .A1(new_n44_), .B0(new_n72_), .B1(i7), .Y(new_n81_));
  NA4        g71(.A(new_n81_), .B(new_n62_), .C(new_n50_), .D(new_n36_), .Y(zori0));
endmodule


