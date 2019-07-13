package com.patronusstudio.akillireklam;

import java.util.List;

public interface IFirebase {

    interface Database{
        void cevredeki_magazalar(List<magazaModel> cevredeki_magazalar);
    }

    interface Authenticate{

        void mesajDondur(String mesaj,boolean sonuc);

        void sifreDegistirme(String mesaj);

    }


}
