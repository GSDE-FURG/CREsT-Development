// Benchmark "9sym/9sym_1_36_0_esp" written by ABC on Wed Mar  8 02:25:25 2023

module \9sym/9sym_1_36_0_esp  ( 
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
  INV        g00(.A(i3), .Y(new_n11_));
  INV        g01(.A(i4), .Y(new_n12_));
  NO2        g02(.A(i8), .B(new_n12_), .Y(new_n13_));
  INV        g03(.A(i5), .Y(new_n14_));
  NO2        g04(.A(new_n14_), .B(i4), .Y(new_n15_));
  AOI210     g05(.A0(new_n15_), .A1(new_n11_), .B0(new_n13_), .Y(new_n16_));
  NO2        g06(.A(new_n16_), .B(i1), .Y(new_n17_));
  INV        g07(.A(i0), .Y(new_n18_));
  INV        g08(.A(i1), .Y(new_n19_));
  NO2        g09(.A(new_n19_), .B(new_n18_), .Y(new_n20_));
  NO2        g10(.A(i8), .B(i3), .Y(new_n21_));
  AOI210     g11(.A0(new_n21_), .A1(new_n20_), .B0(new_n15_), .Y(new_n22_));
  NO3        g12(.A(i6), .B(new_n12_), .C(i3), .Y(new_n23_));
  INV        g13(.A(i2), .Y(new_n24_));
  NO2        g14(.A(new_n24_), .B(i0), .Y(new_n25_));
  INV        g15(.A(i6), .Y(new_n26_));
  NO2        g16(.A(new_n26_), .B(i5), .Y(new_n27_));
  AOI210     g17(.A0(new_n27_), .A1(new_n25_), .B0(new_n23_), .Y(new_n28_));
  OAI210     g18(.A0(new_n22_), .A1(i2), .B0(new_n28_), .Y(new_n29_));
  OAI210     g19(.A0(new_n29_), .A1(new_n17_), .B0(i7), .Y(new_n30_));
  OR2        g20(.A(i7), .B(i2), .Y(new_n31_));
  AOI210     g21(.A0(new_n31_), .A1(i5), .B0(new_n18_), .Y(new_n32_));
  NO4        g22(.A(i8), .B(i7), .C(new_n14_), .D(new_n12_), .Y(new_n33_));
  OAI210     g23(.A0(new_n33_), .A1(new_n32_), .B0(new_n26_), .Y(new_n34_));
  NO2        g24(.A(i4), .B(new_n24_), .Y(new_n35_));
  NAi21      g25(.An(i7), .B(i6), .Y(new_n36_));
  NO2        g26(.A(new_n36_), .B(i2), .Y(new_n37_));
  NO2        g27(.A(i8), .B(new_n14_), .Y(new_n38_));
  OAI210     g28(.A0(new_n37_), .A1(new_n35_), .B0(new_n38_), .Y(new_n39_));
  OAI210     g29(.A0(i5), .A1(i2), .B0(new_n36_), .Y(new_n40_));
  NA3        g30(.A(new_n40_), .B(new_n31_), .C(new_n19_), .Y(new_n41_));
  OAI210     g31(.A0(i8), .A1(i2), .B0(i5), .Y(new_n42_));
  NA3        g32(.A(new_n42_), .B(i1), .C(new_n18_), .Y(new_n43_));
  NA4        g33(.A(new_n43_), .B(new_n41_), .C(new_n39_), .D(new_n34_), .Y(new_n44_));
  NA2        g34(.A(i6), .B(new_n12_), .Y(new_n45_));
  NO2        g35(.A(i6), .B(i3), .Y(new_n46_));
  NA2        g36(.A(new_n46_), .B(i2), .Y(new_n47_));
  OA210      g37(.A0(new_n45_), .A1(i8), .B0(new_n47_), .Y(new_n48_));
  NO2        g38(.A(i8), .B(i5), .Y(new_n49_));
  NO2        g39(.A(i7), .B(i3), .Y(new_n50_));
  NO3        g40(.A(new_n50_), .B(new_n49_), .C(new_n26_), .Y(new_n51_));
  NO2        g41(.A(i6), .B(new_n18_), .Y(new_n52_));
  NO2        g42(.A(i6), .B(i2), .Y(new_n53_));
  OR3        g43(.A(new_n53_), .B(new_n52_), .C(new_n12_), .Y(new_n54_));
  OAI220     g44(.A0(new_n54_), .A1(new_n51_), .B0(new_n48_), .B1(new_n18_), .Y(new_n55_));
  AOI210     g45(.A0(new_n44_), .A1(i3), .B0(new_n55_), .Y(new_n56_));
  INV        g46(.A(i7), .Y(new_n57_));
  OAI210     g47(.A0(new_n53_), .A1(new_n57_), .B0(i0), .Y(new_n58_));
  NOi21      g48(.An(i5), .B(i0), .Y(new_n59_));
  NO3        g49(.A(new_n50_), .B(new_n46_), .C(i2), .Y(new_n60_));
  OAI210     g50(.A0(new_n59_), .A1(new_n11_), .B0(new_n60_), .Y(new_n61_));
  AOI210     g51(.A0(new_n61_), .A1(new_n58_), .B0(i1), .Y(new_n62_));
  OAI210     g52(.A0(i5), .A1(i3), .B0(i0), .Y(new_n63_));
  AOI220     g53(.A0(new_n63_), .A1(i2), .B0(new_n52_), .B1(new_n57_), .Y(new_n64_));
  NOi41      g54(.An(i7), .B(i5), .C(i3), .D(i2), .Y(new_n65_));
  AOI210     g55(.A0(new_n59_), .A1(new_n26_), .B0(new_n65_), .Y(new_n66_));
  OAI220     g56(.A0(i7), .A1(i5), .B0(i3), .B1(i0), .Y(new_n67_));
  NA3        g57(.A(new_n67_), .B(i4), .C(new_n24_), .Y(new_n68_));
  OA210      g58(.A0(new_n66_), .A1(new_n19_), .B0(new_n68_), .Y(new_n69_));
  OAI210     g59(.A0(new_n64_), .A1(i4), .B0(new_n69_), .Y(new_n70_));
  OAI210     g60(.A0(new_n70_), .A1(new_n62_), .B0(i8), .Y(new_n71_));
  NA3        g61(.A(i5), .B(i4), .C(new_n11_), .Y(new_n72_));
  AO210      g62(.A0(new_n72_), .A1(new_n36_), .B0(i0), .Y(new_n73_));
  NA2        g63(.A(new_n15_), .B(i2), .Y(new_n74_));
  AOI210     g64(.A0(new_n74_), .A1(new_n73_), .B0(i8), .Y(new_n75_));
  NO3        g65(.A(i7), .B(new_n14_), .C(i3), .Y(new_n76_));
  AOI210     g66(.A0(new_n53_), .A1(new_n13_), .B0(new_n76_), .Y(new_n77_));
  OAI220     g67(.A0(new_n77_), .A1(new_n18_), .B0(new_n45_), .B1(i2), .Y(new_n78_));
  OAI210     g68(.A0(new_n78_), .A1(new_n75_), .B0(i1), .Y(new_n79_));
  NA4        g69(.A(new_n79_), .B(new_n71_), .C(new_n56_), .D(new_n30_), .Y(zori0));
endmodule


