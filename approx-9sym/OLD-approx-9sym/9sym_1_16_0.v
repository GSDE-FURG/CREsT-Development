// Benchmark "9sym/9sym_1_16_0_esp" written by ABC on Wed Mar  8 02:19:49 2023

module \9sym/9sym_1_16_0_esp  ( 
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
    new_n102_, new_n103_;
  INV        g00(.A(i8), .Y(new_n11_));
  INV        g01(.A(i2), .Y(new_n12_));
  NO2        g02(.A(i7), .B(new_n12_), .Y(new_n13_));
  NOi21      g03(.An(i7), .B(i5), .Y(new_n14_));
  NOi21      g04(.An(i5), .B(i0), .Y(new_n15_));
  OA210      g05(.A0(new_n15_), .A1(new_n14_), .B0(new_n12_), .Y(new_n16_));
  OAI210     g06(.A0(new_n16_), .A1(new_n13_), .B0(i3), .Y(new_n17_));
  INV        g07(.A(i0), .Y(new_n18_));
  INV        g08(.A(i6), .Y(new_n19_));
  NOi21      g09(.An(i5), .B(i7), .Y(new_n20_));
  NOi21      g10(.An(i7), .B(i2), .Y(new_n21_));
  AOI210     g11(.A0(new_n21_), .A1(new_n19_), .B0(new_n20_), .Y(new_n22_));
  INV        g12(.A(i3), .Y(new_n23_));
  NA2        g13(.A(new_n23_), .B(i2), .Y(new_n24_));
  INV        g14(.A(i5), .Y(new_n25_));
  NO2        g15(.A(i6), .B(new_n25_), .Y(new_n26_));
  NO2        g16(.A(new_n26_), .B(new_n14_), .Y(new_n27_));
  OA220      g17(.A0(new_n27_), .A1(new_n24_), .B0(new_n22_), .B1(new_n18_), .Y(new_n28_));
  AOI210     g18(.A0(new_n28_), .A1(new_n17_), .B0(i1), .Y(new_n29_));
  INV        g19(.A(i1), .Y(new_n30_));
  NO3        g20(.A(i7), .B(i4), .C(new_n12_), .Y(new_n31_));
  OAI210     g21(.A0(new_n31_), .A1(new_n26_), .B0(new_n18_), .Y(new_n32_));
  NO2        g22(.A(i7), .B(new_n18_), .Y(new_n33_));
  INV        g23(.A(i4), .Y(new_n34_));
  NO2        g24(.A(new_n34_), .B(i2), .Y(new_n35_));
  AO210      g25(.A0(new_n35_), .A1(new_n18_), .B0(new_n33_), .Y(new_n36_));
  NO2        g26(.A(i6), .B(i4), .Y(new_n37_));
  AOI220     g27(.A0(new_n37_), .A1(new_n14_), .B0(new_n36_), .B1(new_n23_), .Y(new_n38_));
  AOI210     g28(.A0(new_n38_), .A1(new_n32_), .B0(new_n30_), .Y(new_n39_));
  NOi21      g29(.An(i4), .B(i5), .Y(new_n40_));
  NO2        g30(.A(i4), .B(new_n23_), .Y(new_n41_));
  OAI210     g31(.A0(new_n41_), .A1(new_n40_), .B0(new_n33_), .Y(new_n42_));
  NA3        g32(.A(new_n35_), .B(i5), .C(new_n23_), .Y(new_n43_));
  AOI210     g33(.A0(new_n43_), .A1(new_n42_), .B0(i6), .Y(new_n44_));
  INV        g34(.A(i7), .Y(new_n45_));
  NOi21      g35(.An(i3), .B(i2), .Y(new_n46_));
  AN3        g36(.A(new_n46_), .B(new_n40_), .C(new_n45_), .Y(new_n47_));
  NO4        g37(.A(new_n47_), .B(new_n44_), .C(new_n39_), .D(new_n29_), .Y(new_n48_));
  NOi21      g38(.An(i8), .B(i5), .Y(new_n49_));
  AOI210     g39(.A0(new_n49_), .A1(new_n45_), .B0(new_n21_), .Y(new_n50_));
  OAI210     g40(.A0(i5), .A1(i3), .B0(i8), .Y(new_n51_));
  NOi21      g41(.An(i2), .B(i1), .Y(new_n52_));
  AOI220     g42(.A0(new_n52_), .A1(new_n49_), .B0(new_n51_), .B1(i0), .Y(new_n53_));
  OAI210     g43(.A0(new_n50_), .A1(new_n23_), .B0(new_n53_), .Y(new_n54_));
  NO2        g44(.A(i8), .B(i7), .Y(new_n55_));
  NO3        g45(.A(i5), .B(i3), .C(i2), .Y(new_n56_));
  AOI210     g46(.A0(new_n55_), .A1(new_n18_), .B0(new_n56_), .Y(new_n57_));
  NOi31      g47(.An(i5), .B(i7), .C(i2), .Y(new_n58_));
  AOI220     g48(.A0(new_n58_), .A1(i8), .B0(new_n14_), .B1(i2), .Y(new_n59_));
  OAI220     g49(.A0(new_n59_), .A1(i0), .B0(new_n57_), .B1(new_n30_), .Y(new_n60_));
  AOI210     g50(.A0(new_n54_), .A1(new_n34_), .B0(new_n60_), .Y(new_n61_));
  OAI210     g51(.A0(new_n58_), .A1(new_n52_), .B0(i3), .Y(new_n62_));
  NAi21      g52(.An(new_n40_), .B(new_n62_), .Y(new_n63_));
  AOI220     g53(.A0(new_n21_), .A1(i8), .B0(new_n15_), .B1(i2), .Y(new_n64_));
  OAI220     g54(.A0(new_n64_), .A1(i1), .B0(i7), .B1(new_n34_), .Y(new_n65_));
  AOI220     g55(.A0(new_n65_), .A1(new_n23_), .B0(new_n63_), .B1(new_n11_), .Y(new_n66_));
  AOI210     g56(.A0(new_n66_), .A1(new_n61_), .B0(new_n19_), .Y(new_n67_));
  NA2        g57(.A(new_n25_), .B(i2), .Y(new_n68_));
  NA3        g58(.A(i7), .B(new_n34_), .C(i3), .Y(new_n69_));
  AOI210     g59(.A0(new_n69_), .A1(new_n68_), .B0(i1), .Y(new_n70_));
  OAI210     g60(.A0(new_n40_), .A1(new_n20_), .B0(new_n46_), .Y(new_n71_));
  OAI210     g61(.A0(new_n24_), .A1(i8), .B0(new_n71_), .Y(new_n72_));
  OAI210     g62(.A0(new_n72_), .A1(new_n70_), .B0(i0), .Y(new_n73_));
  AOI210     g63(.A0(new_n55_), .A1(i3), .B0(new_n14_), .Y(new_n74_));
  NOi21      g64(.An(i3), .B(i5), .Y(new_n75_));
  NO3        g65(.A(new_n75_), .B(new_n74_), .C(new_n34_), .Y(new_n76_));
  NOi21      g66(.An(i2), .B(i4), .Y(new_n77_));
  NOi21      g67(.An(i7), .B(i3), .Y(new_n78_));
  NOi21      g68(.An(i4), .B(i8), .Y(new_n79_));
  NOi21      g69(.An(i0), .B(i2), .Y(new_n80_));
  AOI220     g70(.A0(new_n80_), .A1(new_n79_), .B0(new_n78_), .B1(new_n77_), .Y(new_n81_));
  AOI220     g71(.A0(i4), .A1(i2), .B0(i3), .B1(i1), .Y(new_n82_));
  OAI220     g72(.A0(new_n82_), .A1(i0), .B0(new_n81_), .B1(new_n30_), .Y(new_n83_));
  NO2        g73(.A(new_n83_), .B(new_n76_), .Y(new_n84_));
  AOI210     g74(.A0(new_n84_), .A1(new_n73_), .B0(i6), .Y(new_n85_));
  NA2        g75(.A(i4), .B(new_n12_), .Y(new_n86_));
  NA2        g76(.A(new_n77_), .B(i3), .Y(new_n87_));
  OAI220     g77(.A0(new_n87_), .A1(i1), .B0(new_n86_), .B1(i8), .Y(new_n88_));
  NOi21      g78(.An(i5), .B(i4), .Y(new_n89_));
  OAI210     g79(.A0(i3), .A1(i1), .B0(i2), .Y(new_n90_));
  AOI220     g80(.A0(new_n90_), .A1(new_n89_), .B0(new_n88_), .B1(new_n18_), .Y(new_n91_));
  NOi31      g81(.An(i7), .B(i8), .C(i3), .Y(new_n92_));
  OAI210     g82(.A0(new_n92_), .A1(new_n89_), .B0(new_n80_), .Y(new_n93_));
  OAI210     g83(.A0(new_n11_), .A1(new_n18_), .B0(new_n75_), .Y(new_n94_));
  NOi31      g84(.An(i5), .B(i3), .C(i0), .Y(new_n95_));
  OAI210     g85(.A0(new_n79_), .A1(new_n77_), .B0(new_n95_), .Y(new_n96_));
  NA3        g86(.A(new_n96_), .B(new_n94_), .C(new_n93_), .Y(new_n97_));
  NA3        g87(.A(i4), .B(new_n30_), .C(i0), .Y(new_n98_));
  NA2        g88(.A(new_n11_), .B(i5), .Y(new_n99_));
  AOI210     g89(.A0(new_n98_), .A1(new_n87_), .B0(new_n99_), .Y(new_n100_));
  AOI210     g90(.A0(new_n97_), .A1(i1), .B0(new_n100_), .Y(new_n101_));
  OAI210     g91(.A0(new_n91_), .A1(new_n45_), .B0(new_n101_), .Y(new_n102_));
  NO3        g92(.A(new_n102_), .B(new_n85_), .C(new_n67_), .Y(new_n103_));
  OAI210     g93(.A0(new_n48_), .A1(new_n11_), .B0(new_n103_), .Y(zori0));
endmodule


