package Controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Album;
import Model.Music;
import Model.MusicDAO;
import Model.Singer;
import Model.SingerDAO;
import Model.insta;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MainController implements Initializable {
	// ===========첫번째화면===================
	public Stage mainStage;
	@FXML
	private TableView<Music> tableView;
	@FXML
	private TextField txtSinger;
	@FXML
	private TextField txtMusicName;
	@FXML
	private TextField txtAlbumName;
	@FXML
	private TextField txtMusicFile;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnPlay;
	@FXML
	private DatePicker datePicker;
	@FXML
	private Button btnMusicPlay;
	@FXML
	private Button btnMusicPause;
	@FXML
	private Button btnMusicStop;
	@FXML
	private Button btnFileAdd;
	@FXML
	private MediaView mediaView;
	@FXML
	private Slider slider;

	private File selectFile = null;
	private String fileName = "";
	private File imageDirectory = new File("D:/images");
	private Music selectMusic;
	private int selectMusicIndex;
	ObservableList<Music> t1ListData = FXCollections.observableArrayList();
	ArrayList<Music> dbArrayList;

	ObservableList<Singer> t2ListData = FXCollections.observableArrayList();
	ObservableList<Album> t3ListData = FXCollections.observableArrayList();
	ArrayList<Singer> dbArrayList2;
	ArrayList<Album> dbArrayList3;
	ArrayList<insta> dbArrayList4;
	// ================2번째페이지===========
	@FXML
	private Button btnLook01;
	@FXML
	private Button btnLook02;
	@FXML
	private Button btnLook03;
	@FXML
	private Button btnLook04;
	@FXML
	private Button btnLook05;
	@FXML
	private Button btnLook06;
	@FXML
	private Button btnChart;
	private Singer selectSinger;
	private int selectSingerindex;
	Singer singer;
	@FXML
	private ImageView imgSinger01;
	@FXML
	private ImageView imgSinger02;
	@FXML
	private ImageView imgSinger03;
	@FXML
	private ImageView imgSinger04;
	@FXML
	private ImageView imgSinger05;
	@FXML
	private ImageView imgSinger06;
	@FXML
	private Tab tabSecond;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 1. 테이블뷰 세팅
		setTab1TableView();
		// 2.버튼과 입력필드 초기설정
		setButtonTextFieldInitiate("처음");
		// 추가 버튼을 누르면 값이 입력되는 함수
		btnAdd.setOnAction((e) -> {
			handleBtnAddAction();
		});
		// 파일추가 버튼을 눌렀을때 원하는 파일을 가져온다
		/* btnFileAdd.setOnAction((e)->{handleBtnFileAddAction();}); */
		// 재생버튼을 눌렀을때 해당되는 가수의이름과,곡명이 같을경우 노래재생
		btnPlay.setOnAction((e) -> {
			handleBtnPlayAction();
		});
		// 수정버튼을 눌렀을때 해당되는 테이블뷰의 값을 수정한다.
		btnEdit.setOnAction((e) -> {
			handleBtnEditAction();
		});
		// 삭제버튼을 눌렀을때 테이블뷰의 값이 삭제된다.
		btnDelete.setOnAction((e) -> {
			handleBtnDeleteAction();
		});
		
		//테이블뷰에 있는 노래를 선택하면 왼쪽에 값이 띄워진다.
		tableView.setOnMouseClicked((e) -> {
			handleTableViewAction(e);
		});
		// 1번 보기 버튼을 눌렀을때 프로필띄우기
		btnLook01.setOnAction((e) -> {
			handleBtnLook01Action();
		});
		// 2번 보기 버튼을 눌렀을때 프로필띄우기
		btnLook02.setOnAction((e) -> {
			handleBtnLook02Action();
		});
		// 3번 보기 버튼을 눌렀을때 프로필띄우기
		btnLook03.setOnAction((e) -> {
			handleBtnLook03Action();
		});
		// 4번 보기 버튼을 눌렀을때 프로필띄우기
		btnLook04.setOnAction((e) -> {
			handleBtnLook04Action();
		});
		// 5번 보기 버튼을 눌렀을때 프로필띄우기
		btnLook05.setOnAction((e) -> {
			handleBtnLook05Action();
		});
		// 6번 보기 버튼을 눌렀을때 프로필띄우기
		btnLook06.setOnAction((e) -> {
			handleBtnLook06Action();
		});
		
		btnChart.setOnAction((e)->{
			handleBtnChartAction();
		});
		
	}

	/*
	 * private void handleBtnFileAddAction() { FileChooser fileChooser = new
	 * FileChooser(); fileChooser.getExtensionFilters().add(new
	 * ExtensionFilter("Music File", ".mp3")); selectFile =
	 * fileChooser.showOpenDialog(mainStage); String localURL = null; if(selectFile
	 * != null) { try { localURL=selectFile.toURI().toURL().toString(); } catch
	 * (MalformedURLException e) {
	 * 
	 * } }
	 * 
	 * }
	 */


	private void setTab1TableView() {
		TableColumn tcNo = tableView.getColumns().get(0);
		tcNo.setCellValueFactory(new PropertyValueFactory<>("no"));
		tcNo.setStyle("-fx-alignment: CENTER;");

		TableColumn tcName = tableView.getColumns().get(1);
		tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tcName.setStyle("-fx-alignment: CENTER;");

		TableColumn tcSong = tableView.getColumns().get(2);
		tcSong.setCellValueFactory(new PropertyValueFactory<>("song"));
		tcSong.setStyle("-fx-alignment: CENTER;");

		TableColumn tcAlbum = tableView.getColumns().get(3);
		tcAlbum.setCellValueFactory(new PropertyValueFactory<>("album"));
		tcAlbum.setStyle("-fx-alignment: CENTER;");

		TableColumn tcDate = tableView.getColumns().get(4);
		tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		tcDate.setStyle("-fx-alignment: CENTER;");

		TableColumn tcUrl = tableView.getColumns().get(5);
		tcUrl.setCellValueFactory(new PropertyValueFactory<>("url"));
		tcUrl.setStyle("-fx-alignment: CENTER;");

		dbArrayList = MusicDAO.getMusicData();
		for (Music music : dbArrayList) {
			t1ListData.add(music);
		}

		tableView.setItems(t1ListData);
	}

	private void setButtonTextFieldInitiate(String message) {
		switch (message) {
		case "처음":
			btnAdd.setDisable(false);
			btnEdit.setDisable(true);
			btnDelete.setDisable(true);
			break;
		case "수정 및 삭제":
			btnAdd.setDisable(false);
			btnEdit.setDisable(false);
			btnDelete.setDisable(false);
			break;
		}
	}

	private void handleBtnAddAction() {
		int musicNo = 0;
		Music music = new Music(musicNo, txtSinger.getText(), txtMusicName.getText(), txtAlbumName.getText(),
				datePicker.getValue().toString(), txtMusicFile.getText());
		t1ListData.add(music);

		int count = MusicDAO.insertMusicData(music);
		if (count != 0) {
			/*callAlert("추가완료 : 음악이 정상적으로 추가되었습니다.");*/
			txtSinger.clear();
			txtMusicName.clear();
			txtAlbumName.clear();
			txtMusicFile.clear();
			datePicker.setValue(null);
		}
	}

	private void handleBtnPlayAction() {
		Parent root = null;
		try {
		selectMusic = tableView.getSelectionModel().getSelectedItem();
		selectMusicIndex = tableView.getSelectionModel().getSelectedIndex();
		try {
			Stage musicStage = new Stage(StageStyle.UTILITY);
			musicStage.initModality(Modality.WINDOW_MODAL);
			musicStage.initOwner(mainStage);
			musicStage.setTitle(selectMusic.getSong() + "재생");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/musicplayer.fxml"));
			
			root = loader.load();
			MediaView mediaView = (MediaView) root.lookup("#mediaView");
			ProgressBar progressBar = (ProgressBar) root.lookup("#progressBar");
			Button btnMusicPlay = (Button) root.lookup("#btnMusicPlay");
			Button btnMusicPause = (Button) root.lookup("#btnMusicPause");
			Button btnMusicStop = (Button) root.lookup("#btnMusicStop");
			Slider slider = (Slider) root.lookup("#slider");
			Label runtime = (Label) root.lookup("#runtime");
			
			Media media = new Media(
					getClass().getResource("../musics/" + selectMusic.getName() + "-" + selectMusic.getSong() + ".mp3")
							.toString());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			mediaView.setMediaPlayer(mediaPlayer);

			mediaPlayer.setOnReady(() -> {
				btnMusicPlay.setDisable(false);
				btnMusicStop.setDisable(true);
				btnMusicPause.setDisable(true);

				slider.setValue(50.0);

				mediaPlayer.currentTimeProperty().addListener(
						(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) -> {
							double duration = newValue.toSeconds() / mediaPlayer.getTotalDuration().toSeconds();
							progressBar.setProgress(duration);
							runtime.setText((int) newValue.toSeconds() + " / "
									+ (int) mediaPlayer.getTotalDuration().toSeconds() + "sec");

						});

			});
			mediaPlayer.setOnPlaying(() -> {
				btnMusicPlay.setDisable(true);
				btnMusicStop.setDisable(false);
				btnMusicPause.setDisable(false);
			});
			mediaPlayer.setOnPaused(() -> {
				btnMusicPlay.setDisable(false);
				btnMusicStop.setDisable(false);
				btnMusicPause.setDisable(true);
			});
			mediaPlayer.setOnStopped(() -> {
				btnMusicPlay.setDisable(false);
				btnMusicStop.setDisable(true);
				btnMusicPause.setDisable(false);
			});
			mediaPlayer.setOnEndOfMedia(() -> {
				btnMusicPlay.setDisable(false);
				btnMusicStop.setDisable(true);
				btnMusicPause.setDisable(false);
				mediaPlayer.stop();
				mediaPlayer.seek(mediaPlayer.getStartTime());
				mediaView.setVisible(true);
				btnMusicPause.setDisable(true);
			});
			btnMusicPlay.setOnAction((ActionEvent event) -> {
				mediaPlayer.play();
				String string = media.getSource();
				int position = string.lastIndexOf(".");
				String extension = string.substring(position + 1);
				switch (extension) {
				case "wav":
				case "mp3":
				case "wma":
					break;
				}
			});
			btnMusicPause.setOnAction((e1) -> {
				mediaPlayer.pause();
			});
			btnMusicStop.setOnAction((e2) -> {
				mediaPlayer.stop();
				mediaView.setVisible(false);
			});
			slider.valueProperty()
					.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
						mediaPlayer.setVolume(newValue.doubleValue() / 100);
					});
			Scene scene = new Scene(root);
			musicStage.setScene(scene);
			musicStage.show();
		} catch (IOException e1) {
			
		}
		}catch(Exception e) {
			callAlert("재생실패 : 폴더에 해당되는 음악이 없습니다 \n"+"가수명과 노래제목을 확인해주세요!");
		}

	}

	private void handleTableViewAction(MouseEvent e) {
		selectMusic = tableView.getSelectionModel().getSelectedItem();
		selectMusicIndex = tableView.getSelectionModel().getSelectedIndex();
		if (e.getClickCount() == 1) {
			txtSinger.setText(selectMusic.getName());
			txtMusicName.setText(selectMusic.getSong());
			txtAlbumName.setText(selectMusic.getAlbum());
			datePicker.setValue(LocalDate.parse(selectMusic.getDate()));
			txtMusicFile.setText(selectMusic.getUrl());
			btnDelete.setDisable(false);
			btnEdit.setDisable(false);
			btnAdd.setDisable(true);
		}else {
			btnAdd.setDisable(false);	
		}

	}

	// 테이블뷰에 있는 값들을 수정한다.
	private void handleBtnEditAction() {
		int musicNo = 0;
		Music music = new Music(musicNo, txtSinger.getText(), txtMusicName.getText(), txtAlbumName.getText(),
				datePicker.getValue().toString(), txtMusicFile.getText());
		int count = MusicDAO.updateMusicData(music);
		if (count != 0) {
			t1ListData.remove(selectMusicIndex);
			t1ListData.add(selectMusicIndex, music);
			int arrayIndex = dbArrayList.indexOf(selectMusic);
			dbArrayList.set(arrayIndex, music);
		} else {
			return;
		}
		setButtonTextFieldInitiate("처음");
		/*callAlert("수정완료 : 선택하신 곡의 정보가 수정되었습니다.");*/
	}

	private void handleBtnDeleteAction() {
		int count = MusicDAO.deleteMusicData(selectMusic.getNo());
		if (count != 0) {
			t1ListData.remove(selectMusicIndex);
			dbArrayList.remove(selectMusic);
			txtSinger.clear();
			txtMusicName.clear();
			txtAlbumName.clear();
			txtMusicFile.clear();
			datePicker.setValue(null);
			/*callAlert("삭제완료 : 선택하신 곡 목록이 삭제되었습니다.");*/
		} else {
			return;
		}
		setButtonTextFieldInitiate("처음");
	}
	
	

	private void handleBtnLook01Action() {
		Parent root;
/*		Singer singer1 = new Singer("singer01.png", "아이유", "1993-01-01", "짱짱뮤직", "우옹", "너랑나");
		selectSinger = singer1;*/

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/singer.fxml"));
			root = loader.load();
			Stage singerStage = new Stage(StageStyle.UTILITY);
			singerStage.initModality(Modality.WINDOW_MODAL);
			singerStage.initOwner(mainStage);
			singerStage.setTitle("프로필");
			Button btn2Edit = (Button) root.lookup("#btn2Edit");
			Button btn2Close = (Button) root.lookup("#btn2Close");
			Button btnImgEdit = (Button) root.lookup("#btnImgEdit");
			ImageView tab2ImgView = (ImageView) root.lookup("#tab2ImgView");
			TextField txt2Name = (TextField) root.lookup("#txt2Name");
			TextField txt2Birth = (TextField) root.lookup("#txt2Birth");
			TextField txt2Agency = (TextField) root.lookup("#txt2Agency");
			TextField txt2Awards = (TextField) root.lookup("#txt2Awards");
			TextArea txt2Hitsong = (TextArea) root.lookup("#txt2Hitsong");

			dbArrayList2 = SingerDAO.selectMusicData("아이유");
			for (Singer singer : dbArrayList2) {
				tab2ImgView.setImage(
						new Image(getClass().getResource("../images/" + singer.getImagePath()).toString()));
				txt2Name.setText(singer.getName2());
				txt2Birth.setText(singer.getBirth());
				txt2Agency.setText(singer.getAgency());
				txt2Awards.setText(singer.getAwards());
				txt2Hitsong.setText(singer.getTopsing());
				txt2Name.setDisable(true);

			}
			btnImgEdit.setOnAction((e) -> {
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
				selectFile = fileChooser.showOpenDialog(mainStage);
				String localURL = null;
				if (selectFile != null) {
					try {
						localURL = selectFile.toURI().toURL().toString();
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				tab2ImgView.setImage(new Image(localURL, false));
				
				fileName = selectFile.getName();
			});

			btn2Edit.setOnAction((e) -> {
				System.out.println("**"+fileName);
				
				Singer singer = new Singer(fileName, txt2Name.getText(), txt2Birth.getText(), txt2Agency.getText(),
						txt2Awards.getText(), txt2Hitsong.getText());
				int count = SingerDAO.updateSingerData(singer,txt2Name.getText());
				/*System.out.println("함수콜"+singer.toString());
				singer.toString();
				System.out.println("*"+selectSinger.getName2());*/
			/*	if (count != 0) {
					dbArrayList2.set(0, singer);
//					tab2ImgView.setImage(new Image(getClass().getResource(fileName).toString()));
//					txt2Birth.setText(singer.getBirth());
					file:/c:/music/fvhgj.mp3
				} else {
					return;
				}*/
				imgSinger01.setImage(new Image("file:///"+imageDirectory.getAbsolutePath()+"/"+singer.getImagePath()));
				/*callAlert("수정완료 : 가수정보가 수정되었습니다.");*/
			});
			btn2Close.setOnAction((e) -> {
				singerStage.close();
			});
			
			Scene scene = new Scene(root);
			singerStage.setScene(scene);
			singerStage.show();
		} catch (IOException e) {

		}
	}

	private void handleBtnLook02Action() {
		Parent root;

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/singer.fxml"));
			root = loader.load();
			Stage singerStage = new Stage(StageStyle.UTILITY);
			singerStage.initModality(Modality.WINDOW_MODAL);
			singerStage.initOwner(mainStage);
			singerStage.setTitle("프로필");
			Button btn2Edit = (Button) root.lookup("#btn2Edit");
			Button btn2Close = (Button) root.lookup("#btn2Close");
			Button btnImgEdit = (Button) root.lookup("#btnImgEdit");
			ImageView tab2ImgView = (ImageView) root.lookup("#tab2ImgView");
			TextField txt2Name = (TextField) root.lookup("#txt2Name");
			TextField txt2Birth = (TextField) root.lookup("#txt2Birth");
			TextField txt2Agency = (TextField) root.lookup("#txt2Agency");
			TextField txt2Awards = (TextField) root.lookup("#txt2Awards");
			TextArea txt2Hitsong = (TextArea) root.lookup("#txt2Hitsong");

			dbArrayList2 = SingerDAO.selectMusicData("먼데이키즈");
			for (Singer singer1 : dbArrayList2) {
				tab2ImgView
						.setImage(new Image(getClass().getResource("../images/" + singer1.getImagePath()).toString()));
				txt2Name.setText(singer1.getName2());
				txt2Birth.setText(singer1.getBirth());
				txt2Agency.setText(singer1.getAgency());
				txt2Awards.setText(singer1.getAwards());
				txt2Hitsong.setText(singer1.getTopsing());
				txt2Name.setDisable(true);
			}
			btnImgEdit.setOnAction((e) -> {
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
				selectFile = fileChooser.showOpenDialog(mainStage);
				String localURL = null;
				if (selectFile != null) {
					try {
						localURL = selectFile.toURI().toURL().toString();
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				tab2ImgView.setImage(new Image(localURL, false));
				fileName = selectFile.getName();
			});

			btn2Edit.setOnAction((e) -> {
				System.out.println("**"+fileName);
				
				Singer singer = new Singer(fileName, txt2Name.getText(), txt2Birth.getText(), txt2Agency.getText(),
						txt2Awards.getText(), txt2Hitsong.getText());
				int count = SingerDAO.updateSingerData(singer,txt2Name.getText());
				
				imgSinger02.setImage(new Image("file:///"+imageDirectory.getAbsolutePath()+"/"+singer.getImagePath()));
				/*callAlert("수정완료 : 가수정보가 수정되었습니다.");*/
			});
			btn2Close.setOnAction((e) -> {
				singerStage.close();
			});
			Scene scene = new Scene(root);
			singerStage.setScene(scene);
			singerStage.show();
		} catch (IOException e) {

		}
	}

	private void handleBtnLook03Action() {
		Parent root;

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/singer.fxml"));
			root = loader.load();
			Stage singerStage = new Stage(StageStyle.UTILITY);
			singerStage.initModality(Modality.WINDOW_MODAL);
			singerStage.initOwner(mainStage);
			singerStage.setTitle("프로필");
			Button btn2Edit = (Button) root.lookup("#btn2Edit");
			Button btn2Close = (Button) root.lookup("#btn2Close");
			Button btnImgEdit = (Button) root.lookup("#btnImgEdit");
			ImageView tab2ImgView = (ImageView) root.lookup("#tab2ImgView");
			TextField txt2Name = (TextField) root.lookup("#txt2Name");
			TextField txt2Birth = (TextField) root.lookup("#txt2Birth");
			TextField txt2Agency = (TextField) root.lookup("#txt2Agency");
			TextField txt2Awards = (TextField) root.lookup("#txt2Awards");
			TextArea txt2Hitsong = (TextArea) root.lookup("#txt2Hitsong");

			dbArrayList2 = SingerDAO.selectMusicData("윤하");
			for (Singer singer2 : dbArrayList2) {
				tab2ImgView
						.setImage(new Image(getClass().getResource("../images/" + singer2.getImagePath()).toString()));
				txt2Name.setText(singer2.getName2());
				txt2Birth.setText(singer2.getBirth());
				txt2Agency.setText(singer2.getAgency());
				txt2Awards.setText(singer2.getAwards());
				txt2Hitsong.setText(singer2.getTopsing());
				txt2Name.setDisable(true);
			}
			btnImgEdit.setOnAction((e) -> {
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
				selectFile = fileChooser.showOpenDialog(mainStage);
				String localURL = null;
				if (selectFile != null) {
					try {
						localURL = selectFile.toURI().toURL().toString();
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				tab2ImgView.setImage(new Image(localURL, false));
				fileName = selectFile.getName();
			});

			btn2Edit.setOnAction((e) -> {
				System.out.println("**"+fileName);
				
				Singer singer = new Singer(fileName, txt2Name.getText(), txt2Birth.getText(), txt2Agency.getText(),
						txt2Awards.getText(), txt2Hitsong.getText());
				int count = SingerDAO.updateSingerData(singer,txt2Name.getText());
				
				imgSinger03.setImage(new Image("file:///"+imageDirectory.getAbsolutePath()+"/"+singer.getImagePath()));
				/*callAlert("수정완료 : 가수정보가 수정되었습니다.");*/
			});
			btn2Close.setOnAction((e) -> {
				singerStage.close();
			});

			btn2Close.setOnAction((e) -> {
				singerStage.close();
			});
			Scene scene = new Scene(root);
			singerStage.setScene(scene);
			singerStage.show();
		} catch (IOException e) {

		}
	}

	private void handleBtnLook04Action() {
		Parent root;

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/singer.fxml"));
			root = loader.load();
			Stage singerStage = new Stage(StageStyle.UTILITY);
			singerStage.initModality(Modality.WINDOW_MODAL);
			singerStage.initOwner(mainStage);
			singerStage.setTitle("프로필");
			Button btn2Edit = (Button) root.lookup("#btn2Edit");
			Button btn2Close = (Button) root.lookup("#btn2Close");
			Button btnImgEdit = (Button) root.lookup("#btnImgEdit");
			ImageView tab2ImgView = (ImageView) root.lookup("#tab2ImgView");
			TextField txt2Name = (TextField) root.lookup("#txt2Name");
			TextField txt2Birth = (TextField) root.lookup("#txt2Birth");
			TextField txt2Agency = (TextField) root.lookup("#txt2Agency");
			TextField txt2Awards = (TextField) root.lookup("#txt2Awards");
			TextArea txt2Hitsong = (TextArea) root.lookup("#txt2Hitsong");

			dbArrayList2 = SingerDAO.selectMusicData("버즈(민경훈)");
			for (Singer singer3 : dbArrayList2) {
				tab2ImgView
						.setImage(new Image(getClass().getResource("../images/" + singer3.getImagePath()).toString()));
				txt2Name.setText(singer3.getName2());
				txt2Birth.setText(singer3.getBirth());
				txt2Agency.setText(singer3.getAgency());
				txt2Awards.setText(singer3.getAwards());
				txt2Hitsong.setText(singer3.getTopsing());
				txt2Name.setDisable(true);
			}
			btnImgEdit.setOnAction((e) -> {
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
				selectFile = fileChooser.showOpenDialog(mainStage);
				String localURL = null;
				if (selectFile != null) {
					try {
						localURL = selectFile.toURI().toURL().toString();
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				tab2ImgView.setImage(new Image(localURL, false));
				fileName = selectFile.getName();
			});

			btn2Edit.setOnAction((e) -> {
				System.out.println("**"+fileName);
				
				Singer singer = new Singer(fileName, txt2Name.getText(), txt2Birth.getText(), txt2Agency.getText(),
						txt2Awards.getText(), txt2Hitsong.getText());
				int count = SingerDAO.updateSingerData(singer,txt2Name.getText());
				
				imgSinger04.setImage(new Image("file:///"+imageDirectory.getAbsolutePath()+"/"+singer.getImagePath()));
				/*callAlert("수정완료 : 가수정보가 수정되었습니다.");*/
			});
			btn2Close.setOnAction((e) -> {
				singerStage.close();
			});
			btn2Close.setOnAction((e) -> {
				singerStage.close();
			});
			Scene scene = new Scene(root);
			singerStage.setScene(scene);
			singerStage.show();
		} catch (IOException e) {

		}
	}

	private void handleBtnLook05Action() {
		Parent root;

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/singer.fxml"));
			root = loader.load();
			Stage singerStage = new Stage(StageStyle.UTILITY);
			singerStage.initModality(Modality.WINDOW_MODAL);
			singerStage.initOwner(mainStage);
			singerStage.setTitle("프로필");
			Button btn2Edit = (Button) root.lookup("#btn2Edit");
			Button btn2Close = (Button) root.lookup("#btn2Close");
			Button btnImgEdit = (Button) root.lookup("#btnImgEdit");
			ImageView tab2ImgView = (ImageView) root.lookup("#tab2ImgView");
			TextField txt2Name = (TextField) root.lookup("#txt2Name");
			TextField txt2Birth = (TextField) root.lookup("#txt2Birth");
			TextField txt2Agency = (TextField) root.lookup("#txt2Agency");
			TextField txt2Awards = (TextField) root.lookup("#txt2Awards");
			TextArea txt2Hitsong = (TextArea) root.lookup("#txt2Hitsong");

			dbArrayList2 = SingerDAO.selectMusicData("엠씨더맥스(이수)");
			for (Singer singer4 : dbArrayList2) {
				tab2ImgView
						.setImage(new Image(getClass().getResource("../images/" + singer4.getImagePath()).toString()));
				txt2Name.setText(singer4.getName2());
				txt2Birth.setText(singer4.getBirth());
				txt2Agency.setText(singer4.getAgency());
				txt2Awards.setText(singer4.getAwards());
				txt2Hitsong.setText(singer4.getTopsing());
				txt2Name.setDisable(true);
			}
			btnImgEdit.setOnAction((e) -> {
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
				selectFile = fileChooser.showOpenDialog(mainStage);
				String localURL = null;
				if (selectFile != null) {
					try {
						localURL = selectFile.toURI().toURL().toString();
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				tab2ImgView.setImage(new Image(localURL, false));
				fileName = selectFile.getName();
			});

			btn2Edit.setOnAction((e) -> {
				System.out.println("**"+fileName);
				
				Singer singer = new Singer(fileName, txt2Name.getText(), txt2Birth.getText(), txt2Agency.getText(),
						txt2Awards.getText(), txt2Hitsong.getText());
				int count = SingerDAO.updateSingerData(singer,txt2Name.getText());
				
				imgSinger05.setImage(new Image("file:///"+imageDirectory.getAbsolutePath()+"/"+singer.getImagePath()));
				/*callAlert("수정완료 : 가수정보가 수정되었습니다.");*/
			});
			btn2Close.setOnAction((e) -> {
				singerStage.close();
			});
			btn2Close.setOnAction((e) -> {
				singerStage.close();
			});
			Scene scene = new Scene(root);
			singerStage.setScene(scene);
			singerStage.show();
		} catch (IOException e) {

		}
	}

	private void handleBtnLook06Action() {
		Parent root;

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/singer.fxml"));
			root = loader.load();
			Stage singerStage = new Stage(StageStyle.UTILITY);
			singerStage.initModality(Modality.WINDOW_MODAL);
			singerStage.initOwner(mainStage);
			singerStage.setTitle("프로필");
			Button btn2Edit = (Button) root.lookup("#btn2Edit");
			Button btn2Close = (Button) root.lookup("#btn2Close");
			Button btnImgEdit = (Button) root.lookup("#btnImgEdit");
			ImageView tab2ImgView = (ImageView) root.lookup("#tab2ImgView");
			TextField txt2Name = (TextField) root.lookup("#txt2Name");
			TextField txt2Birth = (TextField) root.lookup("#txt2Birth");
			TextField txt2Agency = (TextField) root.lookup("#txt2Agency");
			TextField txt2Awards = (TextField) root.lookup("#txt2Awards");
			TextArea txt2Hitsong = (TextArea) root.lookup("#txt2Hitsong");

			dbArrayList2 = SingerDAO.selectMusicData("허각");
			for (Singer singer5 : dbArrayList2) {
				tab2ImgView
						.setImage(new Image(getClass().getResource("../images/" + singer5.getImagePath()).toString()));
				txt2Name.setText(singer5.getName2());
				txt2Birth.setText(singer5.getBirth());
				txt2Agency.setText(singer5.getAgency());
				txt2Awards.setText(singer5.getAwards());
				txt2Hitsong.setText(singer5.getTopsing());
				txt2Name.setDisable(true);
			}
			btnImgEdit.setOnAction((e) -> {
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
				selectFile = fileChooser.showOpenDialog(mainStage);
				String localURL = null;
				if (selectFile != null) {
					try {
						localURL = selectFile.toURI().toURL().toString();
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				tab2ImgView.setImage(new Image(localURL, false));
				fileName = selectFile.getName();
			});

			btn2Edit.setOnAction((e) -> {
				System.out.println("**"+fileName);
				
				Singer singer = new Singer(fileName, txt2Name.getText(), txt2Birth.getText(), txt2Agency.getText(),
						txt2Awards.getText(), txt2Hitsong.getText());
				int count = SingerDAO.updateSingerData(singer,txt2Name.getText());
				
				imgSinger06.setImage(new Image("file:///"+imageDirectory.getAbsolutePath()+"/"+singer.getImagePath()));
				/*callAlert("수정완료 : 가수정보가 수정되었습니다.");*/
			});
		
			btn2Close.setOnAction((e) -> {
				singerStage.close();
			});
			Scene scene = new Scene(root);
			singerStage.setScene(scene);
			singerStage.show();
		} catch (IOException e) {
			
		}
	}

	private void handleBtnChartAction() {
		
		try {
		Stage bcStage=new Stage(StageStyle.UTILITY);
		bcStage.initModality(Modality.WINDOW_MODAL);
		bcStage.initOwner(mainStage);
		bcStage.setTitle("팔로워차트");
		
		FXMLLoader loader=new FXMLLoader(getClass().getResource("../View/chart.fxml"));
		Parent root = loader.load();
		
		BarChart barChart=(BarChart)root.lookup("#barChart");
		Button btnBarClose=(Button)root.lookup("#btnBarClose");
		ObservableList instaseries = FXCollections.observableArrayList();
		dbArrayList4=SingerDAO.selectFollowerData();
		
		insta insta =dbArrayList4.get(0);
		insta insta1 =dbArrayList4.get(1);
		insta insta2 =dbArrayList4.get(2);
		insta insta3 =dbArrayList4.get(3);
		insta insta4 =dbArrayList4.get(4);
		insta insta5 =dbArrayList4.get(5);
		
		
		XYChart.Series series=new XYChart.Series<>();
		instaseries.add(new XYChart.Data<>(insta.getName(),insta.getFollower()));
		instaseries.add(new XYChart.Data<>(insta1.getName(),insta1.getFollower()));
		instaseries.add(new XYChart.Data<>(insta2.getName(),insta2.getFollower()));
		instaseries.add(new XYChart.Data<>(insta3.getName(),insta3.getFollower()));
		instaseries.add(new XYChart.Data<>(insta4.getName(),insta4.getFollower()));
		instaseries.add(new XYChart.Data<>(insta5.getName(),insta5.getFollower()));
		
		
		series.setData(instaseries);
		
		barChart.getData().add(series);
		
		Scene scene=new Scene(root);
		bcStage.setScene(scene);
		bcStage.show();
		
		btnBarClose.setOnAction((e)->{
			bcStage.close();
		});
		} catch (IOException e) {


		}
	}

	private void imageSave() {
		if (!imageDirectory.exists()) {
			imageDirectory.mkdir(); // 디렉토리가 생성이 안되어 있으면 폴더를 만든다.
		}
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		// 선택된 이미지를 C:/images/"선택된이미지이름명"으로 저장한다.
		try {
			fis = new FileInputStream(selectFile);
			bis = new BufferedInputStream(fis);
			// FileChooser에서 선택된 파일명이 C:/kkkk/pppp/jjjj/이율.jpg(selectFile)였을때
			// selectFile.getName() -> 이율.jpg 만 가져온다.
			// 새로운 파일명을 규정하는데 -> student12345678912_이율.jpg
			// imageDirectoty.getAbsolutePath()+"\\"+fileName
			// -> C:/images/student12345678912_이율.jpg 이름으로 저장한다.
			// C:/kkkk/pppp/jjjj/이율.jpg 파일을 읽어서 C:/images/student12345678912_이율.jpg로 저장한다.
			fileName = selectFile.getName();
			fos = new FileOutputStream(imageDirectory.getAbsolutePath() + "\\" + fileName);
			bos = new BufferedOutputStream(fos);
			int data = -1;
			while ((data = bis.read()) != -1) {
				bos.write(data);
				bos.flush();
			} // end of while
		} catch (Exception e) {
			callAlert("이미지저장 에러 : C/images/저장파일 에러 점검바람");
		} finally {
			if (fis != null) {
				try {
					fis.close();
					bis.close();
					fos.close();
					bos.close();
				} catch (IOException e) {
				}
			}
		} // end of try/catch/finally
	}

	public static void callAlert(String contentText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("경고!");
		alert.setHeaderText(contentText.substring(0, contentText.lastIndexOf(":")));
		alert.setContentText(contentText.substring(contentText.lastIndexOf(":") + 1));

		alert.showAndWait();
	}
}
