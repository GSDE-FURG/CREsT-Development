// Benchmark "decod" written by ABC on Thu Nov  9 16:51:19 2023

module decod ( 
    a, b, c, d, e,
    f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u  );
  input  a, b, c, d, e;
  output f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u;
  wire new_n22_, new_n23_, new_n24_, new_n25_, new_n26_;
  INVx1_ASAP7_75t_R         g00(.A(a), .Y(new_n22_));
  INVx1_ASAP7_75t_R         g01(.A(b), .Y(new_n23_));
  INVx1_ASAP7_75t_R         g02(.A(c), .Y(new_n24_));
  INVx1_ASAP7_75t_R         g03(.A(d), .Y(new_n25_));
  INVx1_ASAP7_75t_R         g04(.A(e), .Y(new_n26_));
  NOR5xp2_ASAP7_75t_R       g05(.A(new_n23_), .B(new_n25_), .C(new_n26_), .D(new_n22_), .E(new_n24_), .Y(f));
  NOR5xp2_ASAP7_75t_R       g06(.A(new_n26_), .B(new_n24_), .C(new_n23_), .D(new_n22_), .E(d), .Y(g));
  NOR5xp2_ASAP7_75t_R       g07(.A(new_n23_), .B(new_n25_), .C(new_n26_), .D(new_n22_), .E(c), .Y(h));
  NOR5xp2_ASAP7_75t_R       g08(.A(new_n26_), .B(new_n23_), .C(new_n22_), .D(d), .E(c), .Y(i));
  NOR5xp2_ASAP7_75t_R       g09(.A(new_n26_), .B(new_n25_), .C(new_n24_), .D(new_n22_), .E(b), .Y(j));
  NOR5xp2_ASAP7_75t_R       g10(.A(new_n26_), .B(new_n24_), .C(new_n22_), .D(d), .E(b), .Y(k));
  NOR5xp2_ASAP7_75t_R       g11(.A(new_n26_), .B(new_n25_), .C(new_n22_), .D(c), .E(b), .Y(l));
  NOR5xp2_ASAP7_75t_R       g12(.A(new_n26_), .B(new_n22_), .C(d), .D(c), .E(b), .Y(m));
  NOR5xp2_ASAP7_75t_R       g13(.A(new_n26_), .B(new_n25_), .C(new_n24_), .D(new_n23_), .E(a), .Y(n));
  NOR5xp2_ASAP7_75t_R       g14(.A(new_n26_), .B(new_n24_), .C(new_n23_), .D(d), .E(a), .Y(o));
  NOR5xp2_ASAP7_75t_R       g15(.A(new_n23_), .B(new_n25_), .C(new_n26_), .D(a), .E(c), .Y(p));
  NOR5xp2_ASAP7_75t_R       g16(.A(new_n26_), .B(new_n23_), .C(d), .D(c), .E(a), .Y(q));
  NOR5xp2_ASAP7_75t_R       g17(.A(new_n26_), .B(new_n25_), .C(new_n24_), .D(b), .E(a), .Y(r));
  NOR5xp2_ASAP7_75t_R       g18(.A(new_n26_), .B(new_n24_), .C(d), .D(b), .E(a), .Y(s));
  NOR5xp2_ASAP7_75t_R       g19(.A(new_n26_), .B(new_n25_), .C(c), .D(b), .E(a), .Y(t));
  NOR5xp2_ASAP7_75t_R       g20(.A(new_n26_), .B(d), .C(c), .D(b), .E(a), .Y(u));
endmodule


