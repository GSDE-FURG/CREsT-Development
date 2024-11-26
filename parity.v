// Benchmark "PARITYFDS" written by ABC on Thu Nov  9 17:00:54 2023

module PARITYFDS ( 
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p,
    q  );
  input  a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p;
  output q;
  wire new_n18_, new_n19_, new_n20_, new_n21_, new_n22_, new_n23_, new_n24_,
    new_n25_, new_n26_, new_n27_, new_n28_, new_n29_, new_n30_, new_n31_;
  XOR2xp5_ASAP7_75t_R       g00(.A(m), .B(n), .Y(new_n18_));
  XOR2xp5_ASAP7_75t_R       g01(.A(o), .B(p), .Y(new_n19_));
  XOR2xp5_ASAP7_75t_R       g02(.A(new_n18_), .B(new_n19_), .Y(new_n20_));
  XOR2xp5_ASAP7_75t_R       g03(.A(k), .B(l), .Y(new_n21_));
  XOR2xp5_ASAP7_75t_R       g04(.A(i), .B(j), .Y(new_n22_));
  XOR2xp5_ASAP7_75t_R       g05(.A(new_n21_), .B(new_n22_), .Y(new_n23_));
  XOR2xp5_ASAP7_75t_R       g06(.A(new_n20_), .B(new_n23_), .Y(new_n24_));
  XOR2xp5_ASAP7_75t_R       g07(.A(e), .B(f), .Y(new_n25_));
  XOR2xp5_ASAP7_75t_R       g08(.A(g), .B(h), .Y(new_n26_));
  XOR2xp5_ASAP7_75t_R       g09(.A(new_n25_), .B(new_n26_), .Y(new_n27_));
  XOR2xp5_ASAP7_75t_R       g10(.A(c), .B(d), .Y(new_n28_));
  XOR2xp5_ASAP7_75t_R       g11(.A(a), .B(b), .Y(new_n29_));
  XOR2xp5_ASAP7_75t_R       g12(.A(new_n28_), .B(new_n29_), .Y(new_n30_));
  XOR2xp5_ASAP7_75t_R       g13(.A(new_n27_), .B(new_n30_), .Y(new_n31_));
  XOR2xp5_ASAP7_75t_R       g14(.A(new_n24_), .B(new_n31_), .Y(q));
endmodule


