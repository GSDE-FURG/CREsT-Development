// Benchmark "cla_unit" written by ABC on Fri May 08 09:54:06 2020

module cla_unit ( 
    c0, a0, a1, a2, a3, b0, b1, b2, b3,
    c1, c2, c3, c4  );
  input  c0, a0, a1, a2, a3, b0, b1, b2, b3;
  output c1, c2, c3, c4;
  wire n13, n14, n15, n17, n18, n19, n20, n21, n22, n24, n25, n26, n27, n28,
    n30, n31, n32, n33, n34, n35, n36, n37;
  INVX1    g00(.A(a0), .Y(n13));
  INVX1    g01(.A(b0), .Y(n14));
  OAI21X1  g02(.A0(b0), .A1(a0), .B0(c0), .Y(n15));
  OAI21X1  g03(.A0(n14), .A1(n13), .B0(n15), .Y(c1));
  NOR2X1   g04(.A(b1), .B(a1), .Y(n17));
  OR2X1    g05(.A(n17), .B(n15), .Y(n18));
  NAND2X1  g06(.A(b1), .B(a1), .Y(n19));
  AND2X1   g07(.A(b0), .B(a0), .Y(n20));
  OR2X1    g08(.A(b1), .B(a1), .Y(n21));
  NAND2X1  g09(.A(n21), .B(n20), .Y(n22));
  NAND3X1  g10(.A(n22), .B(n19), .C(n18), .Y(c2));
  NOR2X1   g11(.A(b2), .B(a2), .Y(n24));
  NOR3X1   g12(.A(n24), .B(n17), .C(n15), .Y(n25));
  NOR4X1   g13(.A(n17), .B(n14), .C(n13), .D(n24), .Y(n26));
  AND2X1   g14(.A(b2), .B(a2), .Y(n27));
  NOR2X1   g15(.A(n24), .B(n19), .Y(n28));
  OR4X1    g16(.A(n27), .B(n26), .C(n25), .D(n28), .Y(c3));
  NOR2X1   g17(.A(b3), .B(a3), .Y(n30));
  OR4X1    g18(.A(n24), .B(n17), .C(n15), .D(n30), .Y(n31));
  OR2X1    g19(.A(b2), .B(a2), .Y(n32));
  OR2X1    g20(.A(b3), .B(a3), .Y(n33));
  NAND4X1  g21(.A(n32), .B(n21), .C(n20), .D(n33), .Y(n34));
  NAND4X1  g22(.A(n32), .B(b1), .C(a1), .D(n33), .Y(n35));
  AND2X1   g23(.A(b3), .B(a3), .Y(n36));
  AOI21X1  g24(.A0(n33), .A1(n27), .B0(n36), .Y(n37));
  NAND4X1  g25(.A(n35), .B(n34), .C(n31), .D(n37), .Y(c4));
endmodule


