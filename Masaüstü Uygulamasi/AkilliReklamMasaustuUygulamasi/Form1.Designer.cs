namespace AkilliReklamMasaustuUygulamasi
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.btn_ekle = new System.Windows.Forms.Button();
            this.txt_firma_id = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.txt_firma_adi = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.txt_kampanya_icerigi = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.txt_kampanya_suresi = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.txt_firma_lokasyon = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.txt_bulunan_firma_sayisi = new System.Windows.Forms.Label();
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.button1 = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // btn_ekle
            // 
            this.btn_ekle.Location = new System.Drawing.Point(142, 353);
            this.btn_ekle.Name = "btn_ekle";
            this.btn_ekle.Size = new System.Drawing.Size(155, 23);
            this.btn_ekle.TabIndex = 0;
            this.btn_ekle.Text = "Güncelle";
            this.btn_ekle.UseVisualStyleBackColor = true;
            this.btn_ekle.Click += new System.EventHandler(this.Button1_Click_1);
            // 
            // txt_firma_id
            // 
            this.txt_firma_id.Location = new System.Drawing.Point(142, 105);
            this.txt_firma_id.Name = "txt_firma_id";
            this.txt_firma_id.Size = new System.Drawing.Size(155, 20);
            this.txt_firma_id.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(36, 108);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(46, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Firma ID";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(36, 165);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(50, 13);
            this.label2.TabIndex = 3;
            this.label2.Text = "Firma Adı";
            // 
            // txt_firma_adi
            // 
            this.txt_firma_adi.Location = new System.Drawing.Point(142, 162);
            this.txt_firma_adi.Name = "txt_firma_adi";
            this.txt_firma_adi.Size = new System.Drawing.Size(155, 20);
            this.txt_firma_adi.TabIndex = 4;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(36, 265);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(87, 13);
            this.label3.TabIndex = 5;
            this.label3.Text = "Kampanya içeriği";
            // 
            // txt_kampanya_icerigi
            // 
            this.txt_kampanya_icerigi.Location = new System.Drawing.Point(142, 262);
            this.txt_kampanya_icerigi.Name = "txt_kampanya_icerigi";
            this.txt_kampanya_icerigi.Size = new System.Drawing.Size(155, 20);
            this.txt_kampanya_icerigi.TabIndex = 6;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(36, 314);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(89, 13);
            this.label4.TabIndex = 7;
            this.label4.Text = "Kampanya Süresi";
            // 
            // txt_kampanya_suresi
            // 
            this.txt_kampanya_suresi.Location = new System.Drawing.Point(142, 311);
            this.txt_kampanya_suresi.Name = "txt_kampanya_suresi";
            this.txt_kampanya_suresi.Size = new System.Drawing.Size(155, 20);
            this.txt_kampanya_suresi.TabIndex = 8;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(36, 214);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(53, 13);
            this.label5.TabIndex = 9;
            this.label5.Text = "Lokasyon";
            // 
            // txt_firma_lokasyon
            // 
            this.txt_firma_lokasyon.Location = new System.Drawing.Point(142, 211);
            this.txt_firma_lokasyon.Name = "txt_firma_lokasyon";
            this.txt_firma_lokasyon.Size = new System.Drawing.Size(155, 20);
            this.txt_firma_lokasyon.TabIndex = 10;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(36, 56);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(104, 13);
            this.label6.TabIndex = 14;
            this.label6.Text = "Bulunan Firma Sayısı";
            // 
            // txt_bulunan_firma_sayisi
            // 
            this.txt_bulunan_firma_sayisi.AutoSize = true;
            this.txt_bulunan_firma_sayisi.Location = new System.Drawing.Point(193, 56);
            this.txt_bulunan_firma_sayisi.Name = "txt_bulunan_firma_sayisi";
            this.txt_bulunan_firma_sayisi.Size = new System.Drawing.Size(13, 13);
            this.txt_bulunan_firma_sayisi.TabIndex = 18;
            this.txt_bulunan_firma_sayisi.Text = "0";
            // 
            // dataGridView1
            // 
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Location = new System.Drawing.Point(330, 55);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.Size = new System.Drawing.Size(282, 383);
            this.dataGridView1.TabIndex = 19;
            this.dataGridView1.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.DataGridView1_CellContentClick);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(142, 394);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(155, 23);
            this.button1.TabIndex = 20;
            this.button1.Text = "Getir";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.Button1_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(658, 450);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.dataGridView1);
            this.Controls.Add(this.txt_bulunan_firma_sayisi);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.txt_firma_lokasyon);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.txt_kampanya_suresi);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.txt_kampanya_icerigi);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.txt_firma_adi);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.txt_firma_id);
            this.Controls.Add(this.btn_ekle);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btn_ekle;
        private System.Windows.Forms.TextBox txt_firma_id;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox txt_firma_adi;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox txt_kampanya_icerigi;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox txt_kampanya_suresi;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox txt_firma_lokasyon;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label txt_bulunan_firma_sayisi;
        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.Button button1;
    }
}

