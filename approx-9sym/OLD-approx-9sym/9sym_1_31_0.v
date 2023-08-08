// Benchmark "9sym/9sym_1_31_0_esp" written by ABC on Wed Mar  8 02:24:22 2023

module \9sym/9sym_1_31_0_esp  ( 
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
  INV        g03(.A(i3), .Y(new_n14_));
  INV        g04(.A(i6), .Y(new_n15_));
  NO3        g05(.A(new_n15_), .B(i5), .C(new_n14_), .Y(new_n16_));
  INV        g06(.A(i0), .Y(new_n17_));
  NO2        g07(.A(i6), .B(new_n17_), .Y(new_n18_));
  OAI210     g08(.A0(new_n18_), .A1(new_n16_), .B0(new_n13_), .Y(new_n19_));
  AOI210     g09(.A0(new_n19_), .A1(new_n12_), .B0(i4), .Y(new_n20_));
  INV        g10(.A(i2), .Y(new_n21_));
  OAI210     g11(.A0(i6), .A1(i2), .B0(i7), .Y(new_n22_));
  NA2        g12(.A(i5), .B(i3), .Y(new_n23_));
  NAi31      g13(.An(i3), .B(i7), .C(i6), .Y(new_n24_));
  OAI210     g14(.A0(new_n23_), .A1(i0), .B0(new_n24_), .Y(new_n25_));
  AOI220     g15(.A0(new_n25_), .A1(new_n21_), .B0(new_n22_), .B1(i0), .Y(new_n26_));
  NAi21      g16(.An(i5), .B(i7), .Y(new_n27_));
  OR2        g17(.A(i3), .B(i2), .Y(new_n28_));
  NAi21      g18(.An(i0), .B(i5), .Y(new_n29_));
  OAI220     g19(.A0(new_n29_), .A1(i6), .B0(new_n28_), .B1(new_n27_), .Y(new_n30_));
  OAI220     g20(.A0(i7), .A1(i5), .B0(i3), .B1(i0), .Y(new_n31_));
  INV        g21(.A(i4), .Y(new_n32_));
  NO2        g22(.A(new_n32_), .B(i2), .Y(new_n33_));
  AOI220     g23(.A0(new_n33_), .A1(new_n31_), .B0(new_n30_), .B1(i1), .Y(new_n34_));
  OAI210     g24(.A0(new_n26_), .A1(i1), .B0(new_n34_), .Y(new_n35_));
  OAI210     g25(.A0(new_n35_), .A1(new_n20_), .B0(i8), .Y(new_n36_));
  NAi32      g26(.An(i3), .Bn(i1), .C(i5), .Y(new_n37_));
  AOI210     g27(.A0(new_n37_), .A1(new_n27_), .B0(i0), .Y(new_n38_));
  NO3        g28(.A(i7), .B(new_n14_), .C(i1), .Y(new_n39_));
  OAI210     g29(.A0(new_n39_), .A1(new_n38_), .B0(i2), .Y(new_n40_));
  INV        g30(.A(i1), .Y(new_n41_));
  NA2        g31(.A(new_n32_), .B(new_n21_), .Y(new_n42_));
  INV        g32(.A(i8), .Y(new_n43_));
  NA3        g33(.A(new_n43_), .B(new_n13_), .C(new_n17_), .Y(new_n44_));
  AO210      g34(.A0(new_n44_), .A1(new_n42_), .B0(new_n41_), .Y(new_n45_));
  OR2        g35(.A(i7), .B(i2), .Y(new_n46_));
  OAI220     g36(.A0(new_n46_), .A1(new_n23_), .B0(i4), .B1(new_n17_), .Y(new_n47_));
  OAI220     g37(.A0(i8), .A1(i5), .B0(i7), .B1(i3), .Y(new_n48_));
  AOI220     g38(.A0(new_n48_), .A1(i4), .B0(new_n47_), .B1(new_n43_), .Y(new_n49_));
  NA3        g39(.A(new_n49_), .B(new_n45_), .C(new_n40_), .Y(new_n50_));
  NA2        g40(.A(new_n50_), .B(i6), .Y(new_n51_));
  NA2        g41(.A(i3), .B(new_n21_), .Y(new_n52_));
  NOi21      g42(.An(i5), .B(i3), .Y(new_n53_));
  NA2        g43(.A(new_n53_), .B(i4), .Y(new_n54_));
  AOI210     g44(.A0(new_n54_), .A1(new_n52_), .B0(i0), .Y(new_n55_));
  INV        g45(.A(i5), .Y(new_n56_));
  NO2        g46(.A(new_n56_), .B(i4), .Y(new_n57_));
  AO220      g47(.A0(new_n57_), .A1(i2), .B0(new_n33_), .B1(new_n18_), .Y(new_n58_));
  OAI210     g48(.A0(new_n58_), .A1(new_n55_), .B0(i1), .Y(new_n59_));
  OAI210     g49(.A0(i7), .A1(i6), .B0(i4), .Y(new_n60_));
  NAi31      g50(.An(new_n23_), .B(new_n60_), .C(new_n42_), .Y(new_n61_));
  AO210      g51(.A0(new_n61_), .A1(new_n59_), .B0(i8), .Y(new_n62_));
  NO2        g52(.A(i8), .B(new_n32_), .Y(new_n63_));
  NO3        g53(.A(new_n56_), .B(i4), .C(i3), .Y(new_n64_));
  OAI210     g54(.A0(new_n64_), .A1(new_n63_), .B0(new_n41_), .Y(new_n65_));
  NO4        g55(.A(i8), .B(i3), .C(new_n41_), .D(new_n17_), .Y(new_n66_));
  OAI210     g56(.A0(new_n66_), .A1(new_n57_), .B0(new_n21_), .Y(new_n67_));
  NA2        g57(.A(new_n56_), .B(i3), .Y(new_n68_));
  AOI210     g58(.A0(new_n21_), .A1(new_n41_), .B0(new_n43_), .Y(new_n69_));
  NA2        g59(.A(new_n15_), .B(i4), .Y(new_n70_));
  OA220      g60(.A0(new_n70_), .A1(i3), .B0(new_n69_), .B1(new_n68_), .Y(new_n71_));
  NA3        g61(.A(new_n71_), .B(new_n67_), .C(new_n65_), .Y(new_n72_));
  NA3        g62(.A(new_n53_), .B(new_n13_), .C(i1), .Y(new_n73_));
  NOi21      g63(.An(new_n46_), .B(new_n23_), .Y(new_n74_));
  NA2        g64(.A(new_n28_), .B(new_n15_), .Y(new_n75_));
  OAI210     g65(.A0(new_n75_), .A1(new_n74_), .B0(new_n73_), .Y(new_n76_));
  OAI220     g66(.A0(new_n70_), .A1(new_n21_), .B0(new_n68_), .B1(new_n41_), .Y(new_n77_));
  MUX2       g67(.S(i0), .A(new_n77_), .B(new_n76_), .Y(new_n78_));
  AOI210     g68(.A0(new_n72_), .A1(i7), .B0(new_n78_), .Y(new_n79_));
  NA4        g69(.A(new_n79_), .B(new_n62_), .C(new_n51_), .D(new_n36_), .Y(zori0));
endmodule


