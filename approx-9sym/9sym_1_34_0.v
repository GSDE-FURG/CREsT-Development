// Benchmark "9sym/9sym_1_34_0_esp" written by ABC on Wed Mar  8 02:25:00 2023

module \9sym/9sym_1_34_0_esp  ( 
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
    new_n74_, new_n75_, new_n76_;
  INV        g00(.A(i5), .Y(new_n11_));
  NO2        g01(.A(i7), .B(i2), .Y(new_n12_));
  OAI210     g02(.A0(new_n12_), .A1(new_n11_), .B0(i0), .Y(new_n13_));
  INV        g03(.A(i7), .Y(new_n14_));
  INV        g04(.A(i8), .Y(new_n15_));
  NA4        g05(.A(new_n15_), .B(new_n14_), .C(i5), .D(i4), .Y(new_n16_));
  AOI210     g06(.A0(new_n16_), .A1(new_n13_), .B0(i6), .Y(new_n17_));
  NA2        g07(.A(new_n15_), .B(i5), .Y(new_n18_));
  INV        g08(.A(i2), .Y(new_n19_));
  NOi21      g09(.An(i2), .B(i4), .Y(new_n20_));
  NOi21      g10(.An(i6), .B(i7), .Y(new_n21_));
  AOI210     g11(.A0(new_n21_), .A1(new_n19_), .B0(new_n20_), .Y(new_n22_));
  NO2        g12(.A(new_n21_), .B(new_n19_), .Y(new_n23_));
  INV        g13(.A(i1), .Y(new_n24_));
  NAi21      g14(.An(i2), .B(i5), .Y(new_n25_));
  NAi31      g15(.An(new_n12_), .B(new_n25_), .C(new_n24_), .Y(new_n26_));
  OAI220     g16(.A0(new_n26_), .A1(new_n23_), .B0(new_n22_), .B1(new_n18_), .Y(new_n27_));
  OAI210     g17(.A0(new_n27_), .A1(new_n17_), .B0(i3), .Y(new_n28_));
  NOi21      g18(.An(i0), .B(i8), .Y(new_n29_));
  NOi21      g19(.An(i1), .B(i6), .Y(new_n30_));
  NOi21      g20(.An(i8), .B(i5), .Y(new_n31_));
  AOI220     g21(.A0(new_n31_), .A1(new_n14_), .B0(new_n30_), .B1(new_n29_), .Y(new_n32_));
  AOI220     g22(.A0(i7), .A1(new_n24_), .B0(i6), .B1(new_n11_), .Y(new_n33_));
  OAI220     g23(.A0(new_n33_), .A1(i8), .B0(new_n32_), .B1(i2), .Y(new_n34_));
  NA2        g24(.A(new_n34_), .B(i4), .Y(new_n35_));
  INV        g25(.A(i4), .Y(new_n36_));
  OAI210     g26(.A0(i6), .A1(i2), .B0(i1), .Y(new_n37_));
  AOI210     g27(.A0(new_n18_), .A1(i2), .B0(new_n37_), .Y(new_n38_));
  AOI210     g28(.A0(i8), .A1(new_n14_), .B0(i6), .Y(new_n39_));
  AN2        g29(.A(i8), .B(i6), .Y(new_n40_));
  NAi21      g30(.An(new_n40_), .B(i0), .Y(new_n41_));
  OAI220     g31(.A0(new_n41_), .A1(new_n39_), .B0(new_n25_), .B1(new_n14_), .Y(new_n42_));
  OAI210     g32(.A0(new_n42_), .A1(new_n38_), .B0(new_n36_), .Y(new_n43_));
  OAI210     g33(.A0(i6), .A1(i2), .B0(i7), .Y(new_n44_));
  NA4        g34(.A(new_n44_), .B(i8), .C(new_n24_), .D(i0), .Y(new_n45_));
  NA4        g35(.A(new_n45_), .B(new_n43_), .C(new_n35_), .D(new_n28_), .Y(new_n46_));
  NO3        g36(.A(new_n31_), .B(new_n29_), .C(new_n24_), .Y(new_n47_));
  OAI210     g37(.A0(new_n40_), .A1(i1), .B0(new_n19_), .Y(new_n48_));
  NOi21      g38(.An(i4), .B(i6), .Y(new_n49_));
  NO2        g39(.A(i4), .B(i1), .Y(new_n50_));
  AOI210     g40(.A0(new_n50_), .A1(i5), .B0(new_n49_), .Y(new_n51_));
  OAI210     g41(.A0(new_n48_), .A1(new_n47_), .B0(new_n51_), .Y(new_n52_));
  NA2        g42(.A(new_n52_), .B(i7), .Y(new_n53_));
  NA2        g43(.A(i5), .B(i1), .Y(new_n54_));
  OAI220     g44(.A0(new_n54_), .A1(i7), .B0(i6), .B1(new_n19_), .Y(new_n55_));
  NA3        g45(.A(new_n14_), .B(i6), .C(i4), .Y(new_n56_));
  NA2        g46(.A(new_n31_), .B(new_n20_), .Y(new_n57_));
  NA2        g47(.A(new_n57_), .B(new_n56_), .Y(new_n58_));
  AOI210     g48(.A0(new_n55_), .A1(i0), .B0(new_n58_), .Y(new_n59_));
  AOI210     g49(.A0(new_n59_), .A1(new_n53_), .B0(i3), .Y(new_n60_));
  NA3        g50(.A(i8), .B(i3), .C(new_n19_), .Y(new_n61_));
  INV        g51(.A(i3), .Y(new_n62_));
  NA3        g52(.A(i6), .B(new_n62_), .C(i2), .Y(new_n63_));
  NA3        g53(.A(new_n63_), .B(new_n61_), .C(new_n24_), .Y(new_n64_));
  AOI210     g54(.A0(i4), .A1(new_n62_), .B0(i8), .Y(new_n65_));
  OAI210     g55(.A0(new_n65_), .A1(new_n40_), .B0(i1), .Y(new_n66_));
  NA3        g56(.A(new_n66_), .B(new_n64_), .C(i5), .Y(new_n67_));
  NA3        g57(.A(i7), .B(i6), .C(new_n11_), .Y(new_n68_));
  AOI210     g58(.A0(i8), .A1(new_n36_), .B0(new_n49_), .Y(new_n69_));
  AOI210     g59(.A0(new_n69_), .A1(new_n68_), .B0(new_n19_), .Y(new_n70_));
  AOI220     g60(.A0(new_n21_), .A1(new_n15_), .B0(new_n11_), .B1(i3), .Y(new_n71_));
  AOI220     g61(.A0(new_n15_), .A1(i1), .B0(i4), .B1(new_n62_), .Y(new_n72_));
  OAI210     g62(.A0(i8), .A1(i3), .B0(new_n19_), .Y(new_n73_));
  OAI220     g63(.A0(new_n73_), .A1(new_n72_), .B0(new_n71_), .B1(new_n24_), .Y(new_n74_));
  NO2        g64(.A(new_n74_), .B(new_n70_), .Y(new_n75_));
  AOI210     g65(.A0(new_n75_), .A1(new_n67_), .B0(i0), .Y(new_n76_));
  OR3        g66(.A(new_n76_), .B(new_n60_), .C(new_n46_), .Y(zori0));
endmodule


