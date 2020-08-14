// Benchmark "b01" written by ABC on Fri Jan  4 12:39:27 2019

module b01 ( clock, 
    LINE1, LINE2,
    OUTP_REG, OVERFLW_REG  );
  input  clock;
  input  LINE1, LINE2;
  output OUTP_REG, OVERFLW_REG;
  reg OVERFLW_REG, STATO_REG_2_, STATO_REG_1_, STATO_REG_0_, OUTP_REG;
  wire n20, n22, n23, n24_1, n25, n26, n28, n29_1, n30, n31, n32, n33, n34,
    n36, n37, n38, n39, n40, n41, n42, n44, n45, n10, n14, n19, n24, n29;
  INVX1    g00(.A(STATO_REG_2_), .Y(n20));
  AND3X1   g01(.A(STATO_REG_0_), .B(STATO_REG_1_), .C(n20), .Y(n10));
  NOR3X1   g02(.A(STATO_REG_0_), .B(LINE2), .C(LINE1), .Y(n22));
  OR3X1    g03(.A(n22), .B(STATO_REG_1_), .C(n20), .Y(n23));
  INVX1    g04(.A(STATO_REG_0_), .Y(n24_1));
  AND2X1   g05(.A(LINE2), .B(LINE1), .Y(n25));
  AOI21X1  g06(.A0(n24_1), .A1(STATO_REG_1_), .B0(n25), .Y(n26));
  OAI21X1  g07(.A0(n26), .A1(STATO_REG_2_), .B0(n23), .Y(n14));
  NOR2X1   g08(.A(LINE2), .B(LINE1), .Y(n28));
  OAI21X1  g09(.A0(n28), .A1(STATO_REG_0_), .B0(STATO_REG_2_), .Y(n29_1));
  AOI21X1  g10(.A0(n28), .A1(STATO_REG_1_), .B0(n29_1), .Y(n30));
  AND2X1   g11(.A(n24_1), .B(STATO_REG_1_), .Y(n31));
  OR2X1    g12(.A(n25), .B(n20), .Y(n32));
  AND2X1   g13(.A(n32), .B(n31), .Y(n33));
  NOR3X1   g14(.A(n25), .B(n24_1), .C(STATO_REG_1_), .Y(n34));
  OR3X1    g15(.A(n34), .B(n33), .C(n30), .Y(n19));
  NOR2X1   g16(.A(n25), .B(n24_1), .Y(n36));
  AND2X1   g17(.A(n25), .B(n24_1), .Y(n37));
  NOR4X1   g18(.A(n37), .B(n36), .C(STATO_REG_1_), .D(STATO_REG_2_), .Y(n38));
  INVX1    g19(.A(STATO_REG_1_), .Y(n39));
  NOR4X1   g20(.A(n25), .B(n24_1), .C(n39), .D(STATO_REG_2_), .Y(n40));
  NOR2X1   g21(.A(n28), .B(n20), .Y(n41));
  MX2X1    g22(.A(n41), .B(n25), .S0(n31), .Y(n42));
  OR3X1    g23(.A(n42), .B(n40), .C(n38), .Y(n24));
  XOR2X1   g24(.A(LINE2), .B(LINE1), .Y(n44));
  AOI21X1  g25(.A0(n24_1), .A1(STATO_REG_1_), .B0(n20), .Y(n45));
  XOR2X1   g26(.A(n45), .B(n44), .Y(n29));
  always @ (posedge clock) begin
    OVERFLW_REG <= n10;
    STATO_REG_2_ <= n14;
    STATO_REG_1_ <= n19;
    STATO_REG_0_ <= n24;
    OUTP_REG <= n29;
  end
endmodule


