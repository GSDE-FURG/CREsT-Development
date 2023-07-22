// Benchmark "9sym/9sym_1_24_0_esp" written by ABC on Wed Mar  8 02:21:50 2023

module \9sym/9sym_1_24_0_esp  ( 
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
    new_n88_, new_n89_, new_n90_, new_n91_;
  INV        g00(.A(i3), .Y(new_n11_));
  INV        g01(.A(i6), .Y(new_n12_));
  INV        g02(.A(i5), .Y(new_n13_));
  NO2        g03(.A(i4), .B(i1), .Y(new_n14_));
  OA210      g04(.A0(new_n14_), .A1(new_n13_), .B0(i0), .Y(new_n15_));
  NOi21      g05(.An(i1), .B(i2), .Y(new_n16_));
  NOi21      g06(.An(i4), .B(i8), .Y(new_n17_));
  AOI210     g07(.A0(new_n17_), .A1(i5), .B0(new_n16_), .Y(new_n18_));
  NO2        g08(.A(new_n18_), .B(i7), .Y(new_n19_));
  OAI210     g09(.A0(new_n19_), .A1(new_n15_), .B0(new_n12_), .Y(new_n20_));
  OAI210     g10(.A0(i8), .A1(i2), .B0(i5), .Y(new_n21_));
  NA2        g11(.A(new_n21_), .B(i1), .Y(new_n22_));
  NOi21      g12(.An(i5), .B(i2), .Y(new_n23_));
  NOi21      g13(.An(i8), .B(i1), .Y(new_n24_));
  AOI220     g14(.A0(new_n24_), .A1(new_n23_), .B0(new_n14_), .B1(i7), .Y(new_n25_));
  AOI210     g15(.A0(new_n25_), .A1(new_n22_), .B0(i0), .Y(new_n26_));
  INV        g16(.A(i8), .Y(new_n27_));
  NOi21      g17(.An(i6), .B(i7), .Y(new_n28_));
  AOI210     g18(.A0(new_n28_), .A1(new_n13_), .B0(new_n27_), .Y(new_n29_));
  NO2        g19(.A(i8), .B(i5), .Y(new_n30_));
  NO4        g20(.A(new_n30_), .B(new_n29_), .C(new_n23_), .D(i4), .Y(new_n31_));
  INV        g21(.A(i7), .Y(new_n32_));
  NA2        g22(.A(new_n32_), .B(i6), .Y(new_n33_));
  INV        g23(.A(i1), .Y(new_n34_));
  NOi21      g24(.An(i7), .B(i5), .Y(new_n35_));
  OAI210     g25(.A0(new_n35_), .A1(i2), .B0(new_n34_), .Y(new_n36_));
  AOI210     g26(.A0(new_n33_), .A1(i2), .B0(new_n36_), .Y(new_n37_));
  NO4        g27(.A(new_n33_), .B(i8), .C(new_n13_), .D(i2), .Y(new_n38_));
  NO4        g28(.A(new_n38_), .B(new_n37_), .C(new_n31_), .D(new_n26_), .Y(new_n39_));
  AOI210     g29(.A0(new_n39_), .A1(new_n20_), .B0(new_n11_), .Y(new_n40_));
  NO2        g30(.A(i3), .B(i2), .Y(new_n41_));
  NA2        g31(.A(new_n41_), .B(new_n35_), .Y(new_n42_));
  INV        g32(.A(i0), .Y(new_n43_));
  NA3        g33(.A(new_n12_), .B(i5), .C(new_n43_), .Y(new_n44_));
  AOI210     g34(.A0(new_n44_), .A1(new_n42_), .B0(new_n34_), .Y(new_n45_));
  INV        g35(.A(i2), .Y(new_n46_));
  NOi21      g36(.An(i2), .B(i4), .Y(new_n47_));
  NOi21      g37(.An(i4), .B(i3), .Y(new_n48_));
  AOI220     g38(.A0(new_n48_), .A1(new_n46_), .B0(new_n47_), .B1(new_n32_), .Y(new_n49_));
  INV        g39(.A(i4), .Y(new_n50_));
  NA4        g40(.A(new_n32_), .B(new_n12_), .C(new_n50_), .D(i0), .Y(new_n51_));
  OAI210     g41(.A0(new_n49_), .A1(i0), .B0(new_n51_), .Y(new_n52_));
  OAI210     g42(.A0(new_n48_), .A1(new_n47_), .B0(new_n34_), .Y(new_n53_));
  NOi31      g43(.An(i4), .B(i7), .C(i2), .Y(new_n54_));
  AOI210     g44(.A0(new_n47_), .A1(new_n11_), .B0(new_n54_), .Y(new_n55_));
  AOI210     g45(.A0(new_n55_), .A1(new_n53_), .B0(i5), .Y(new_n56_));
  OAI210     g46(.A0(i6), .A1(i2), .B0(i7), .Y(new_n57_));
  NA2        g47(.A(new_n57_), .B(i0), .Y(new_n58_));
  NA3        g48(.A(new_n41_), .B(i7), .C(i6), .Y(new_n59_));
  AOI210     g49(.A0(new_n59_), .A1(new_n58_), .B0(i1), .Y(new_n60_));
  NO4        g50(.A(new_n60_), .B(new_n56_), .C(new_n52_), .D(new_n45_), .Y(new_n61_));
  NO2        g51(.A(new_n61_), .B(new_n27_), .Y(new_n62_));
  NOi31      g52(.An(i5), .B(i3), .C(i1), .Y(new_n63_));
  OAI210     g53(.A0(new_n63_), .A1(new_n35_), .B0(i2), .Y(new_n64_));
  NA2        g54(.A(i8), .B(i2), .Y(new_n65_));
  NA3        g55(.A(new_n65_), .B(new_n32_), .C(i1), .Y(new_n66_));
  AOI210     g56(.A0(new_n66_), .A1(new_n64_), .B0(i0), .Y(new_n67_));
  NO2        g57(.A(i7), .B(i3), .Y(new_n68_));
  OAI210     g58(.A0(new_n68_), .A1(new_n30_), .B0(i4), .Y(new_n69_));
  AOI210     g59(.A0(new_n27_), .A1(i0), .B0(new_n16_), .Y(new_n70_));
  OAI210     g60(.A0(new_n70_), .A1(i4), .B0(new_n69_), .Y(new_n71_));
  OAI210     g61(.A0(new_n71_), .A1(new_n67_), .B0(i6), .Y(new_n72_));
  NA2        g62(.A(new_n14_), .B(i7), .Y(new_n73_));
  NOi21      g63(.An(i1), .B(i0), .Y(new_n74_));
  OAI210     g64(.A0(new_n47_), .A1(new_n17_), .B0(new_n74_), .Y(new_n75_));
  AOI210     g65(.A0(new_n75_), .A1(new_n73_), .B0(i3), .Y(new_n76_));
  NO2        g66(.A(i8), .B(i1), .Y(new_n77_));
  AOI220     g67(.A0(new_n77_), .A1(i4), .B0(new_n68_), .B1(i1), .Y(new_n78_));
  AOI210     g68(.A0(new_n50_), .A1(new_n46_), .B0(new_n77_), .Y(new_n79_));
  OAI220     g69(.A0(new_n79_), .A1(new_n32_), .B0(new_n78_), .B1(new_n43_), .Y(new_n80_));
  OAI210     g70(.A0(new_n80_), .A1(new_n76_), .B0(i5), .Y(new_n81_));
  AOI220     g71(.A0(new_n17_), .A1(new_n16_), .B0(new_n11_), .B1(i2), .Y(new_n82_));
  NO2        g72(.A(new_n82_), .B(new_n43_), .Y(new_n83_));
  AOI210     g73(.A0(i7), .A1(i1), .B0(i4), .Y(new_n84_));
  NA2        g74(.A(i2), .B(new_n43_), .Y(new_n85_));
  NA2        g75(.A(new_n48_), .B(i7), .Y(new_n86_));
  OAI210     g76(.A0(new_n85_), .A1(new_n84_), .B0(new_n86_), .Y(new_n87_));
  OAI210     g77(.A0(new_n87_), .A1(new_n83_), .B0(new_n12_), .Y(new_n88_));
  NO2        g78(.A(new_n32_), .B(new_n34_), .Y(new_n89_));
  NA4        g79(.A(new_n89_), .B(new_n41_), .C(new_n27_), .D(i0), .Y(new_n90_));
  NA4        g80(.A(new_n90_), .B(new_n88_), .C(new_n81_), .D(new_n72_), .Y(new_n91_));
  OR3        g81(.A(new_n91_), .B(new_n62_), .C(new_n40_), .Y(zori0));
endmodule


