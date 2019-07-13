using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

using FireSharp.Config;
using FireSharp.Interfaces;
using FireSharp.Response;

using System.Collections;

namespace AkilliReklamMasaustuUygulamasi
{
    public partial class Form1 : Form
    {
        DataTable dt = new DataTable();

        public Form1()
        {
            InitializeComponent();
        }

        //Bağlantı gerçekleşip gerçekleşmemesini kontrol diyoruz
        private void Form1_Load(object sender, EventArgs e)
        {

            this.Text = "Akıllı Reklam Kampanya Ekleme";
            client = new FireSharp.FirebaseClient(config);

            if (client != null)
            {
                MessageBox.Show("Bağlantı gerçekleşti");
            }

            dt.Columns.Add("Firma Adı");
            dataGridView1.DataSource = dt;
            this.dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            verileri_getir();

        }



        //Ayarlar-Proje Ayarları-Hizmet hesapları oradan database url ve veritabanı gizli anahtarı al.Buralara ekle
        IFirebaseConfig config = new FirebaseConfig
        {
            AuthSecret = "Veritabanı gizli anahtarı",
            BasePath = "Veritabanı adresi"
        };

        IFirebaseClient client;
        List<firmaObjesi> firma_listesi = new List<firmaObjesi>();







        private async void Button1_Click_1(object sender, EventArgs e)
        {
            firmaObjesi firma = new firmaObjesi
            {
                FirmaAdi = txt_firma_adi.Text,
                FirmaID = txt_firma_id.Text,
                KampanyaIcerik = txt_kampanya_icerigi.Text,
                KampanyaSuresi = txt_kampanya_suresi.Text,
                Lokasyon = txt_firma_lokasyon.Text
            };

            FirebaseResponse response = await client.UpdateTaskAsync("Veritabanında mağaza yolu" + (dataGridView1.CurrentCell.RowIndex+1), firma);

            if (response.Exception != null)
            {
                MessageBox.Show("Error");
            }
            else
            {
                firmaObjesi result = response.ResultAs<firmaObjesi>();
                MessageBox.Show("Veri Eklendi");
            }
        }


        private void Button1_Click(object sender, EventArgs e)
        {
            verileri_getir();
        }

        private async void verileri_getir()
        {

            dt.Rows.Clear();
            firma_listesi.Clear();

            FirebaseResponse sresponse = await client.GetTaskAsync("Veritabanında mağaza yolu");

            magaza_sayisi_objesi magaza_obje = sresponse.ResultAs<magaza_sayisi_objesi>();

            int magaza_sayisi = Convert.ToInt32(magaza_obje.MagazaSayisi);

            txt_bulunan_firma_sayisi.Text = magaza_sayisi.ToString();

            {
                try
                {
                    for (int i = 1; i <= magaza_sayisi; i++)
                    {
                        FirebaseResponse firebaseResponse = await client.GetTaskAsync("Veritabanında mağaza yolu" + i.ToString());

                        firmaObjesi firma_objesi = firebaseResponse.ResultAs<firmaObjesi>();

                        firma_listesi.Add(firma_objesi);

                        DataRow row = dt.NewRow();
                        row["Firma Adı"] = firma_objesi.FirmaAdi;
                        dt.Rows.Add(row);
                    }
                }

                catch
                {

                }

            }
        }

        private void DataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            int tiklanan_konum = dataGridView1.CurrentCell.RowIndex;
            txt_firma_adi.Text = firma_listesi[tiklanan_konum].FirmaAdi;
            txt_firma_id.Text = firma_listesi[tiklanan_konum].FirmaID;
            txt_firma_lokasyon.Text = firma_listesi[tiklanan_konum].Lokasyon;
            txt_kampanya_icerigi.Text = firma_listesi[tiklanan_konum].KampanyaIcerik;
            txt_kampanya_suresi.Text = firma_listesi[tiklanan_konum].KampanyaSuresi;
        }
    }
}



