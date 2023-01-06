package com.example.gtag2;

import com.example.gtag2.Models.CurrentMp3File;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.Mp3File;
import core.GLA;
import core.GetHtmlData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class GTagController implements Initializable {

    @FXML
    private TextField tfSrcField;
    @FXML
    private Label gTitle, gArtist, gYear, gAlbum, gGenre, gUrl, lblgimgDimentions, selectedMusicfile, lblimgDimentions;

    @FXML
    private ImageView gAlbumArt, albumArt;
    @FXML
    private TextArea notherTexts, lblLyrics;

    @FXML
    private ListView<String> musicList;

    @FXML
    private TextField lblTitle, lblArtist, lblYear, lblAlbum, lblGenre;

    private String currentFile, slectedFile;

    ArrayList<String> musicData = new ArrayList();

    CurrentMp3File currentMp3File = new CurrentMp3File();

    ArrayList<CurrentMp3File> musicFileArrayList = new ArrayList();

    private Boolean getwebimage = false;

    String searchq;

    public void choosefile() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(new Stage());
        System.out.println(selectedDirectory.getAbsolutePath());
        slectedFile = selectedDirectory.getAbsolutePath();
        File file = new File(selectedDirectory.getAbsolutePath());
        File l[] = file.listFiles();

        for (File x : l) {
            if (x.getName().endsWith(".mp3")) {
                CurrentMp3File currentMp3File = new CurrentMp3File(x.getPath(), x.getName());
                // System.out.println(x.getPath() + "\\" + x.getName());

                musicData.add(x.getName());
                musicFileArrayList.add(currentMp3File);

            }
        }
        musicList.getItems().addAll(musicData);

    }


    public void findInGenius() {
        parseGenius();

    }

    public void OpenInWeb() {
        //System.out.println(gUrl.getText());
    }

    public void parseGenius() {
        GLA gla = new GLA();
        GetHtmlData getHtmlData = new GetHtmlData();
        searchq = tfSrcField.getText();

        try {
            notherTexts.setText(gla.search(searchq).getHits().get(0).fetchLyrics());
            gTitle.setText(gla.search(searchq).getHits().get(0).getTitle());
            gArtist.setText(gla.search(searchq).getHits().get(0).getArtist().getName());
            gYear.setText(gla.search(searchq).getHits().get(0).getRelease_date_for_display());

            String weburl = gla.search(searchq).getHits().get(0).getUrl();
            gAlbum.setText(getHtmlData.getAlbumName(weburl + "?bagon=1"));
            gGenre.setText(getHtmlData.getGenre(weburl + "?bagon=1"));
            gUrl.setText(weburl);

            Image image3 = new Image(gla.search(searchq).getHits().get(0).getImageUrl());
            image3.getWidth();
            image3.getHeight();
            lblgimgDimentions.setText(image3.getHeight() + "X " + image3.getWidth());

            gAlbumArt.setImage(image3);
            lblimgDimentions.setText(image3.getHeight() + "X " + image3.getWidth());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //musicList.getItems().addAll(filepaths);

        //musicList.getItems().addAll(musicData);
        musicList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                currentMp3File = musicFileArrayList.get(musicList.getSelectionModel().getSelectedIndex());
                currentFile = musicList.getSelectionModel().getSelectedItem();
                String filename = currentFile.replaceAll(" - ", " ").replaceAll(".mp3", "");
                tfSrcField.setText(filename);
                parseGenius();
                readTags();

            }
        });
    }

    public void importTitle() {
        lblTitle.setText(gTitle.getText());

    }

    public void importArtist() {
        lblArtist.setText(gArtist.getText());
    }

    public void importAlbum() {
        lblAlbum.setText(gAlbum.getText());
    }

    public void importYear() {
        lblYear.setText(gYear.getText());
    }

    public void importGenre() {
        lblGenre.setText(gGenre.getText());
    }

    public void importLyrics() {
        lblLyrics.setText(notherTexts.getText());
    }

    public void importAlbumArt() {
        if (!getwebimage) {
            getwebimage = true;
            albumArt.setImage(gAlbumArt.getImage());

        } else {
            getwebimage = false;
            searchq = tfSrcField.getText();
            GLA gla = new GLA();
            Image image3;
            try {
                image3 = new Image(gla.search(searchq).getHits().get(0).getImageUrl());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            albumArt.setImage(image3);
            lblimgDimentions.setText(image3.getHeight() + "X " + image3.getWidth());
        }


    }

    public void importAll() {
        importTitle();
        importArtist();
        importYear();
        importAlbum();
        importGenre();
        importLyrics();
        importAlbumArt();
        getwebimage = true;
    }

    public void Save() {
        savenewTags();
    }

    public void SaveAndmove() {

    }


    private void savenewTags() {

        try {
            Mp3File mp3file = new Mp3File(currentMp3File.getFilepath());


            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            byte[] imageData = id3v2Tag.getAlbumImage();

            ID3v24Tag id3v24Tag = new ID3v24Tag();
            mp3file.setId3v2Tag(id3v24Tag);

            if (mp3file.hasId3v2Tag()) {
                //id3v24Tag = mp3file.getId3v2Tag();
            } else {
                id3v24Tag = new ID3v24Tag();
                mp3file.setId3v2Tag(id3v24Tag);
            }
            if (mp3file.hasId3v2Tag()) {

                id3v24Tag.setArtist(lblArtist.getText());
                id3v24Tag.setTitle(lblTitle.getText());
                id3v24Tag.setAlbum(lblAlbum.getText());
                id3v24Tag.setYear(lblYear.getText());
                id3v24Tag.setGenreDescription(lblGenre.getText());
                id3v24Tag.setLyrics(lblLyrics.getText());


                if (getwebimage) {
                    GLA gla = new GLA();
                    byte[] data;
                    try {
                        URL url = new URL(gla.search(tfSrcField.getText()).getHits().get(0).getImageUrl());
                        BufferedImage bImage = ImageIO.read(url);
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        ImageIO.write(bImage, "jpg", bos);
                        data = bos.toByteArray();
                        id3v24Tag.setAlbumImage(data, "image/jpeg");
                    } catch (IOException e) {
                    }
                }
                else {


                    id3v24Tag.setAlbumImage(imageData, "image/jpeg");


                }


                mp3file.save(slectedFile + "\\Edited\\" + currentMp3File.getFilename());
                getwebimage=false;

                File file = new File(currentMp3File.getFilepath());
                if (file.delete()) {
                    System.out.println("File deleted successfully");
                }
                else {
                    System.out.println("Failed to delete the file");
                }
            }
            //mp3file.save(currentMp3File.getFilepath());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void readTags() {

        try {
            Mp3File mp3file = new Mp3File(currentMp3File.getFilepath());

            if (mp3file.hasId3v2Tag()) {

                ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                lblTitle.setText(id3v2Tag.getTitle());
                lblArtist.setText(id3v2Tag.getArtist());
                lblAlbum.setText(id3v2Tag.getAlbum());
                lblYear.setText(id3v2Tag.getYear());
                lblGenre.setText(id3v2Tag.getGenreDescription());
                lblLyrics.setText(id3v2Tag.getLyrics());

            }

            if (mp3file.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                byte[] imageData = id3v2Tag.getAlbumImage();
                if (imageData != null) {
                    String mimeType = id3v2Tag.getAlbumImageMimeType();
                    //System.out.println("Mime type: " + mimeType);
                    // Write image to file - can determine appropriate file extension from the mime type
                    RandomAccessFile file = new RandomAccessFile("album-artwork", "rw");
                    file.write(imageData);
                    file.close();
                }

                ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                BufferedImage bImage2 = ImageIO.read(bis);

                ImageIcon icon = new ImageIcon(bImage2);

                //Image scaleImage = icon.getImage();
                Image img = new Image(new ByteArrayInputStream(imageData));
                albumArt.setImage(img);


            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
