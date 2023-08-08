// Benchmark "9sym/9sym_1_25_0_esp" written by ABC on Wed Mar  8 02:22:03 2023

module \9sym/9sym_1_25_0_esp  ( 
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
    new_n88_;
  INV        g00(.A(i8), .Y(new_n11_));
  INV        g01(.A(i5), .Y(new_n12_));
  NAi21      g02(.An(i4), .B(i2), .Y(new_n13_));
  NAi21      g03(.An(i7), .B(i6), .Y(new_n14_));
  OAI210     g04(.A0(new_n14_), .A1(i2), .B0(new_n13_), .Y(new_n15_));
  NA2        g05(.A(new_n15_), .B(i3), .Y(new_n16_));
  AOI210     g06(.A0(i4), .A1(i0), .B0(i7), .Y(new_n17_));
  NOi31      g07(.An(i3), .B(i7), .C(i6), .Y(new_n18_));
  NOi31      g08(.An(i1), .B(i3), .C(i0), .Y(new_n19_));
  OAI210     g09(.A0(new_n19_), .A1(new_n18_), .B0(i4), .Y(new_n20_));
  OA210      g10(.A0(new_n17_), .A1(i1), .B0(new_n20_), .Y(new_n21_));
  AOI210     g11(.A0(new_n21_), .A1(new_n16_), .B0(new_n12_), .Y(new_n22_));
  INV        g12(.A(i6), .Y(new_n23_));
  INV        g13(.A(i0), .Y(new_n24_));
  NAi21      g14(.An(i5), .B(i4), .Y(new_n25_));
  OA210      g15(.A0(i4), .A1(new_n24_), .B0(new_n25_), .Y(new_n26_));
  INV        g16(.A(i3), .Y(new_n27_));
  AOI210     g17(.A0(new_n27_), .A1(new_n24_), .B0(i2), .Y(new_n28_));
  INV        g18(.A(i4), .Y(new_n29_));
  INV        g19(.A(i7), .Y(new_n30_));
  OAI220     g20(.A0(new_n30_), .A1(i3), .B0(i6), .B1(new_n29_), .Y(new_n31_));
  AOI210     g21(.A0(new_n31_), .A1(new_n28_), .B0(new_n24_), .Y(new_n32_));
  NO2        g22(.A(i7), .B(new_n23_), .Y(new_n33_));
  OAI210     g23(.A0(new_n28_), .A1(new_n33_), .B0(i1), .Y(new_n34_));
  OAI220     g24(.A0(new_n34_), .A1(new_n32_), .B0(new_n26_), .B1(new_n23_), .Y(new_n35_));
  OAI210     g25(.A0(new_n35_), .A1(new_n22_), .B0(new_n11_), .Y(new_n36_));
  NAi21      g26(.An(i5), .B(i2), .Y(new_n37_));
  NAi31      g27(.An(i2), .B(i7), .C(i6), .Y(new_n38_));
  AOI210     g28(.A0(new_n38_), .A1(new_n37_), .B0(i1), .Y(new_n39_));
  NAi31      g29(.An(i2), .B(i7), .C(i1), .Y(new_n40_));
  AOI210     g30(.A0(new_n40_), .A1(new_n13_), .B0(i5), .Y(new_n41_));
  OAI210     g31(.A0(new_n41_), .A1(new_n39_), .B0(i8), .Y(new_n42_));
  NAi21      g32(.An(i6), .B(i2), .Y(new_n43_));
  NAi31      g33(.An(i7), .B(i5), .C(i1), .Y(new_n44_));
  AOI210     g34(.A0(new_n44_), .A1(new_n43_), .B0(new_n24_), .Y(new_n45_));
  NAi31      g35(.An(i4), .B(i7), .C(i5), .Y(new_n46_));
  NO2        g36(.A(new_n46_), .B(i1), .Y(new_n47_));
  NAi21      g37(.An(i6), .B(i7), .Y(new_n48_));
  AOI210     g38(.A0(new_n48_), .A1(new_n14_), .B0(new_n29_), .Y(new_n49_));
  NO3        g39(.A(new_n49_), .B(new_n47_), .C(new_n45_), .Y(new_n50_));
  AOI210     g40(.A0(new_n50_), .A1(new_n42_), .B0(i3), .Y(new_n51_));
  INV        g41(.A(i1), .Y(new_n52_));
  NAi32      g42(.An(i4), .Bn(i3), .C(i5), .Y(new_n53_));
  AOI210     g43(.A0(new_n53_), .A1(new_n48_), .B0(new_n52_), .Y(new_n54_));
  NAi31      g44(.An(i5), .B(i7), .C(i6), .Y(new_n55_));
  NAi21      g45(.An(i4), .B(i8), .Y(new_n56_));
  OAI210     g46(.A0(new_n56_), .A1(i7), .B0(new_n55_), .Y(new_n57_));
  OAI210     g47(.A0(new_n57_), .A1(new_n54_), .B0(i2), .Y(new_n58_));
  NAi32      g48(.An(i3), .Bn(i2), .C(i8), .Y(new_n59_));
  AOI210     g49(.A0(new_n59_), .A1(new_n43_), .B0(new_n29_), .Y(new_n60_));
  NAi21      g50(.An(i5), .B(i3), .Y(new_n61_));
  NAi31      g51(.An(i6), .B(i8), .C(i5), .Y(new_n62_));
  AOI210     g52(.A0(new_n62_), .A1(new_n61_), .B0(new_n52_), .Y(new_n63_));
  NAi31      g53(.An(i7), .B(i6), .C(i1), .Y(new_n64_));
  NAi41      g54(.An(i1), .B(i8), .C(i5), .D(i3), .Y(new_n65_));
  AOI210     g55(.A0(new_n65_), .A1(new_n64_), .B0(i2), .Y(new_n66_));
  NAi41      g56(.An(i3), .B(i6), .C(i5), .D(i2), .Y(new_n67_));
  NAi31      g57(.An(i4), .B(i7), .C(i3), .Y(new_n68_));
  AOI210     g58(.A0(new_n68_), .A1(new_n67_), .B0(i1), .Y(new_n69_));
  NO4        g59(.A(new_n69_), .B(new_n66_), .C(new_n63_), .D(new_n60_), .Y(new_n70_));
  AOI210     g60(.A0(new_n70_), .A1(new_n58_), .B0(i0), .Y(new_n71_));
  NO2        g61(.A(new_n23_), .B(i4), .Y(new_n72_));
  OAI210     g62(.A0(new_n72_), .A1(new_n18_), .B0(i1), .Y(new_n73_));
  NAi21      g63(.An(i6), .B(i0), .Y(new_n74_));
  OAI220     g64(.A0(new_n74_), .A1(new_n11_), .B0(new_n61_), .B1(new_n30_), .Y(new_n75_));
  NAi21      g65(.An(i7), .B(i8), .Y(new_n76_));
  OAI210     g66(.A0(new_n76_), .A1(new_n25_), .B0(new_n46_), .Y(new_n77_));
  AOI210     g67(.A0(new_n75_), .A1(new_n52_), .B0(new_n77_), .Y(new_n78_));
  AOI210     g68(.A0(new_n78_), .A1(new_n73_), .B0(i2), .Y(new_n79_));
  OAI210     g69(.A0(i4), .A1(i1), .B0(i5), .Y(new_n80_));
  NOi21      g70(.An(new_n80_), .B(new_n74_), .Y(new_n81_));
  INV        g71(.A(i2), .Y(new_n82_));
  OAI220     g72(.A0(new_n56_), .A1(i5), .B0(new_n82_), .B1(i1), .Y(new_n83_));
  AOI210     g73(.A0(new_n83_), .A1(new_n33_), .B0(new_n81_), .Y(new_n84_));
  OAI210     g74(.A0(i6), .A1(i4), .B0(i1), .Y(new_n85_));
  NAi31      g75(.An(new_n76_), .B(new_n85_), .C(i0), .Y(new_n86_));
  OAI210     g76(.A0(new_n84_), .A1(new_n27_), .B0(new_n86_), .Y(new_n87_));
  NO4        g77(.A(new_n87_), .B(new_n79_), .C(new_n71_), .D(new_n51_), .Y(new_n88_));
  NA2        g78(.A(new_n88_), .B(new_n36_), .Y(zori0));
endmodule


