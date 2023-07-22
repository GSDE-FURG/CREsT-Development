// Benchmark "9sym/9sym_1_41_0_esp" written by ABC on Wed Mar  8 02:26:43 2023

module \9sym/9sym_1_41_0_esp  ( 
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
    new_n74_, new_n75_, new_n76_, new_n77_, new_n78_;
  OAI210     g00(.A0(i6), .A1(i2), .B0(i7), .Y(new_n11_));
  NA2        g01(.A(new_n11_), .B(i0), .Y(new_n12_));
  NOi21      g02(.An(i5), .B(i0), .Y(new_n13_));
  NA2        g03(.A(new_n13_), .B(i3), .Y(new_n14_));
  NOi21      g04(.An(i7), .B(i3), .Y(new_n15_));
  NA2        g05(.A(new_n15_), .B(i6), .Y(new_n16_));
  AO210      g06(.A0(new_n16_), .A1(new_n14_), .B0(i2), .Y(new_n17_));
  AOI210     g07(.A0(new_n17_), .A1(new_n12_), .B0(i1), .Y(new_n18_));
  OAI210     g08(.A0(i5), .A1(i3), .B0(i0), .Y(new_n19_));
  INV        g09(.A(i0), .Y(new_n20_));
  NO3        g10(.A(i7), .B(i6), .C(new_n20_), .Y(new_n21_));
  AOI210     g11(.A0(new_n19_), .A1(i2), .B0(new_n21_), .Y(new_n22_));
  INV        g12(.A(i1), .Y(new_n23_));
  INV        g13(.A(i6), .Y(new_n24_));
  NO2        g14(.A(i5), .B(i2), .Y(new_n25_));
  AOI220     g15(.A0(new_n25_), .A1(new_n15_), .B0(new_n13_), .B1(new_n24_), .Y(new_n26_));
  INV        g16(.A(i2), .Y(new_n27_));
  OAI220     g17(.A0(i7), .A1(i5), .B0(i3), .B1(i0), .Y(new_n28_));
  NA3        g18(.A(new_n28_), .B(i4), .C(new_n27_), .Y(new_n29_));
  OA210      g19(.A0(new_n26_), .A1(new_n23_), .B0(new_n29_), .Y(new_n30_));
  OAI210     g20(.A0(new_n22_), .A1(i4), .B0(new_n30_), .Y(new_n31_));
  OAI210     g21(.A0(new_n31_), .A1(new_n18_), .B0(i8), .Y(new_n32_));
  INV        g22(.A(i5), .Y(new_n33_));
  NO2        g23(.A(i7), .B(i2), .Y(new_n34_));
  OAI210     g24(.A0(new_n34_), .A1(new_n33_), .B0(i0), .Y(new_n35_));
  INV        g25(.A(i4), .Y(new_n36_));
  NO2        g26(.A(i8), .B(new_n36_), .Y(new_n37_));
  NO2        g27(.A(i7), .B(new_n33_), .Y(new_n38_));
  NA2        g28(.A(new_n38_), .B(new_n37_), .Y(new_n39_));
  AOI210     g29(.A0(new_n39_), .A1(new_n35_), .B0(i6), .Y(new_n40_));
  NAi21      g30(.An(i7), .B(i6), .Y(new_n41_));
  NA2        g31(.A(new_n41_), .B(new_n27_), .Y(new_n42_));
  NA2        g32(.A(i4), .B(i2), .Y(new_n43_));
  NO2        g33(.A(i8), .B(new_n33_), .Y(new_n44_));
  NA3        g34(.A(new_n44_), .B(new_n43_), .C(new_n42_), .Y(new_n45_));
  NAi21      g35(.An(new_n25_), .B(new_n41_), .Y(new_n46_));
  NO2        g36(.A(new_n34_), .B(i1), .Y(new_n47_));
  NA2        g37(.A(new_n47_), .B(new_n46_), .Y(new_n48_));
  OAI210     g38(.A0(i8), .A1(i2), .B0(i5), .Y(new_n49_));
  NA3        g39(.A(new_n49_), .B(i1), .C(new_n20_), .Y(new_n50_));
  NA3        g40(.A(new_n50_), .B(new_n48_), .C(new_n45_), .Y(new_n51_));
  OAI210     g41(.A0(new_n51_), .A1(new_n40_), .B0(i3), .Y(new_n52_));
  NO3        g42(.A(new_n33_), .B(i4), .C(i3), .Y(new_n53_));
  OAI210     g43(.A0(new_n53_), .A1(new_n37_), .B0(new_n23_), .Y(new_n54_));
  NA4        g44(.A(i6), .B(new_n33_), .C(i2), .D(new_n20_), .Y(new_n55_));
  NO3        g45(.A(i6), .B(new_n36_), .C(i3), .Y(new_n56_));
  NO3        g46(.A(new_n33_), .B(i4), .C(i2), .Y(new_n57_));
  NO2        g47(.A(new_n57_), .B(new_n56_), .Y(new_n58_));
  NA3        g48(.A(new_n58_), .B(new_n55_), .C(new_n54_), .Y(new_n59_));
  NAi21      g49(.An(i4), .B(i6), .Y(new_n60_));
  NO2        g50(.A(new_n60_), .B(i2), .Y(new_n61_));
  NAi31      g51(.An(i3), .B(i5), .C(i4), .Y(new_n62_));
  OR2        g52(.A(i8), .B(i0), .Y(new_n63_));
  AOI210     g53(.A0(new_n62_), .A1(new_n41_), .B0(new_n63_), .Y(new_n64_));
  OAI210     g54(.A0(new_n64_), .A1(new_n61_), .B0(i1), .Y(new_n65_));
  AN2        g55(.A(i3), .B(i0), .Y(new_n66_));
  NO2        g56(.A(i4), .B(i0), .Y(new_n67_));
  NO3        g57(.A(new_n67_), .B(new_n66_), .C(i6), .Y(new_n68_));
  NA2        g58(.A(i5), .B(i1), .Y(new_n69_));
  NO3        g59(.A(new_n69_), .B(i8), .C(i4), .Y(new_n70_));
  OAI210     g60(.A0(new_n70_), .A1(new_n68_), .B0(i2), .Y(new_n71_));
  OR2        g61(.A(i7), .B(i3), .Y(new_n72_));
  OAI220     g62(.A0(new_n72_), .A1(new_n69_), .B0(new_n60_), .B1(i8), .Y(new_n73_));
  NA2        g63(.A(new_n73_), .B(i0), .Y(new_n74_));
  OAI210     g64(.A0(i8), .A1(i5), .B0(new_n72_), .Y(new_n75_));
  NA3        g65(.A(new_n75_), .B(i6), .C(i4), .Y(new_n76_));
  NA4        g66(.A(new_n76_), .B(new_n74_), .C(new_n71_), .D(new_n65_), .Y(new_n77_));
  AOI210     g67(.A0(new_n59_), .A1(i7), .B0(new_n77_), .Y(new_n78_));
  NA3        g68(.A(new_n78_), .B(new_n52_), .C(new_n32_), .Y(zori0));
endmodule


