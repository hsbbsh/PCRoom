package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.income.IncomeDAO;
import model.income.IncomeDTO;

public class IncomeController implements Initializable{
    @FXML
    private Label lbCurrentDate;
    @FXML
    private DatePicker selectDatePicker;
    @FXML
    private Label lbTotalVisitor;
    @FXML
    private Label lbCurrentMember;
    @FXML
    private Label lbPCIncome;
    @FXML
    private Label lbItemIncome;
    @FXML
    private Label lbTotalIncome;
    @FXML
    private PieChart pieChart;
    @FXML
    private TableView<IncomeDTO> tableViewMonthIncome;
    @FXML
    private Label lbMonthYear;
    @FXML
    private Label lbMonth;
    @FXML
    private Label lbRightArrow;
    @FXML
    private Label lbLeftArrow;
    @FXML
    private LineChart<?, ?> lineChart;
    @FXML
    private Label lbYear;
    @FXML
    private Label lbLeftArrowYear;
    @FXML
    private Label lbRightArrowYear;
    @FXML
    private Label lbPCIncomeYear;
    @FXML
    private Label lbItemIncomeYear;
    @FXML
    private Label lbTotalIncomeYear;
    @FXML
    private BarChart<?, ?> barChart;
    
    private IncomeDAO idao = new IncomeDAO();
	private LocalDate date;

	private ObservableList<IncomeDTO> incomeData; // ���̺� �信 �����ֱ� ���ؼ� ����� ������
	
	// lineChart
	XYChart.Series lineMonthPCIncome = new XYChart.Series();
	ObservableList PCIncomeList = FXCollections.observableArrayList();
	XYChart.Series lineMonthItemIncome = new XYChart.Series();
	ObservableList ItemIncomeList = FXCollections.observableArrayList();
	XYChart.Series lineMonthTotalIncome = new XYChart.Series();
	ObservableList totalIncomeList = FXCollections.observableArrayList();

	private int month;
	private int monthYear;
	private int year;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1){
		// �ϸ���
		viewDayIncomeAction();
//		// ������
		viewMonthIncomeAction();
		// ������
		viewYearIncomeAction();

	}
    public void viewDayIncomeAction() {
    	lbCurrentDate.setText(date.now().toString()); // ���糯¥
		lbTotalVisitor.setText(String.valueOf(idao.getDayUserCount(lbCurrentDate.getText()))); // �� �湮��
		lbCurrentMember.setText(String.valueOf(idao.getCurrentMemberCount())); // ���� �� �����ڼ�


		// �� �Ǿ�����
		lbPCIncome.setText(String.valueOf(idao.getDayPCIncomeInfo(lbCurrentDate.getText())));
		// �� ��ǰ����
		lbItemIncome.setText(String.valueOf(idao.getDayItemIncomeInfo(lbCurrentDate.getText())));
		// �� �� ����
		lbTotalIncome.setText(String.valueOf(idao.getDayTotalIncomeInfo(lbCurrentDate.getText())));

		
		selectDatePicker.setOnAction(event -> {
			date = selectDatePicker.getValue(); // ���õ���Ʈ �����ͼ�
			lbCurrentDate.setText("" + date); // ����Ʈ�� �����
			lbTotalVisitor.setText(String.valueOf(idao.getDayUserCount(lbCurrentDate.getText()))); // �� �湮��
			lbCurrentMember.setText(String.valueOf(idao.getCurrentMemberCount())); // ���� �� ������

			// �� �Ǿ�����
			lbPCIncome.setText(String.valueOf(idao.getDayPCIncomeInfo(lbCurrentDate.getText())));
			// �� ��ǰ����
			lbItemIncome.setText(String.valueOf(idao.getDayItemIncomeInfo(lbCurrentDate.getText())));
			// �� �� ����
			lbTotalIncome.setText(String.valueOf(idao.getDayTotalIncomeInfo(lbCurrentDate.getText())));
			
			pieChart.setData(FXCollections.observableArrayList(
					new PieChart.Data("PC", Double.parseDouble(lbPCIncome.getText())),
					new PieChart.Data("��ǰ", Double.parseDouble(lbItemIncome.getText()))));
		});

    }
    public void viewMonthIncomeAction() {
    	incomeData = FXCollections.observableArrayList();
		// 3, ���̺�並 �������ϰ� ����
		tableViewMonthIncome.setEditable(false);
		
		// 5. ���̺������� �÷� ����
		TableColumn colincomeYear = new TableColumn("��");
		colincomeYear.setMaxWidth(70);		
		colincomeYear.setStyle("-fx-alignment: CENTER;");
		colincomeYear.setCellValueFactory(new PropertyValueFactory<>("incomeYear"));

		TableColumn colincomeMonth = new TableColumn("��");
		colincomeMonth.setMaxWidth(70);
		colincomeMonth.setStyle("-fx-alignment: CENTER;");
		colincomeMonth.setCellValueFactory(new PropertyValueFactory<>("incomeMonth"));
		
		TableColumn colincomeDay = new TableColumn("��");
		colincomeDay.setMaxWidth(70);	
		colincomeDay.setStyle("-fx-alignment: CENTER;");
		colincomeDay.setCellValueFactory(new PropertyValueFactory<>("incomeDay"));

		TableColumn coldayPCIncome = new TableColumn("PC����");
		coldayPCIncome.setMaxWidth(100);
		coldayPCIncome.setStyle("-fx-alignment: CENTER;");
		coldayPCIncome.setCellValueFactory(new PropertyValueFactory<>("dayPCIncome"));

		TableColumn coldayItemIncome = new TableColumn("��ǰ����");
		coldayItemIncome.setMaxWidth(100);
		coldayItemIncome.setStyle("-fx-alignment: CENTER;");
		coldayItemIncome.setCellValueFactory(new PropertyValueFactory<>("dayItemIncome"));

		TableColumn coldayTotalIncome = new TableColumn("�Ѹ���");
		coldayTotalIncome.setMaxWidth(100);
		coldayTotalIncome.setStyle("-fx-alignment: CENTER;");
		coldayTotalIncome.setCellValueFactory(new PropertyValueFactory<>("dayTotalIncome"));

		// 5. ���̺��� �÷� ��ü���� ���̺�信 ����Ʈ �߰� �� �׸� �߰��Ѵ�.
		tableViewMonthIncome.setItems(incomeData);
		tableViewMonthIncome.getColumns().addAll(colincomeYear, colincomeMonth, colincomeDay, coldayPCIncome,
		coldayItemIncome, coldayTotalIncome);

		totalIncomeList();
		lineChartReset();
		
		lbRightArrow.setOnMouseClicked(event -> {
			month = Integer.parseInt(lbMonth.getText());
			monthYear = Integer.parseInt(lbMonthYear.getText());
			month++;
			if (month == 13) {
				month = 1;
				monthYear++;
			}
			lbMonth.setText(String.valueOf(month));
			lbMonthYear.setText(String.valueOf(monthYear));
			totalIncomeList();
			lineChartReset();
		});
		lbLeftArrow.setOnMouseClicked(event -> {
			month = Integer.parseInt(lbMonth.getText());
			monthYear = Integer.parseInt(lbMonthYear.getText());
			month--;
			if (month == 0) {
				month = 12;
				monthYear--;
			}
			lbMonth.setText(String.valueOf(month));
			lbMonthYear.setText(String.valueOf(monthYear));
			totalIncomeList();
			lineChartReset();
		});

		
    }
    private void lineChartReset() {
    	// pc ����
    			lineMonthPCIncome.setName("PC����"); // ��Ʈ�� ��
    			PCIncomeList.removeAll(PCIncomeList);
    			for (int i = 0; i < incomeData.size(); i++) {
    				PCIncomeList.add(
    						new XYChart.Data(incomeData.get(i).getIncomeDay(), incomeData.get(i).getDayPCIncome()));
    			}
    			lineMonthPCIncome.setData(PCIncomeList);
    			lineChart.getData().add(lineMonthPCIncome);

    			// ��ǰ����
    			ItemIncomeList.removeAll(ItemIncomeList);
    			lineMonthItemIncome.setName("��ǰ����"); // ��Ʈ�� ��
    			for (int i = 0; i < incomeData.size(); i++) {
    				ItemIncomeList.add(
    						new XYChart.Data(incomeData.get(i).getIncomeDay(), incomeData.get(i).getDayItemIncome()));
    			}
    			lineMonthItemIncome.setData(ItemIncomeList);
    			lineChart.getData().add(lineMonthItemIncome);

    			// �Ѹ���
    			totalIncomeList.removeAll(totalIncomeList);
    			for (int i = 0; i < incomeData.size(); i++) {
    				totalIncomeList.add(
    						new XYChart.Data(incomeData.get(i).getIncomeDay(), incomeData.get(i).getDayTotalIncome()));
    			}
    			lineMonthTotalIncome.setData(totalIncomeList);
    			lineChart.getData().add(lineMonthTotalIncome);   
    }
    private void totalIncomeList() {
		ArrayList<IncomeDTO> list = null;
		IncomeDAO incomeDAO = new IncomeDAO();
		IncomeDTO incomeDTO = null;
		month = Integer.parseInt(lbMonth.getText());
		monthYear = Integer.parseInt(lbMonthYear.getText());

		list = incomeDAO.getIncomeTotal(String.valueOf(monthYear), String.valueOf(month));
//		
		if (list == null) {
			CommonFunc.alertDisplay(1, "���", "DB �������� ����", "�ٽ� �� �� ������ �ּ���.");
			return;
		} else {
			//System.out.println(list.get(0).getDayItemIncome());
			//System.out.println(list.get(0).getDayPCIncome());
			incomeData.removeAll(incomeData);
			for (int i = 0; i < list.size(); i++) {
				incomeDTO = list.get(i);
				
				incomeData.add(incomeDTO);
			}
		}

	}
    public void viewYearIncomeAction() {
		lbPCIncomeYear.setText(String.valueOf(idao.getYearPCIncomeInfo(lbYear.getText())));
		lbItemIncomeYear.setText(String.valueOf(idao.getYearItemIncomeInfo(lbYear.getText())));
		lbTotalIncomeYear.setText(String.valueOf(idao.getYearTotalIncomeInfo(lbYear.getText())));
		
		// PC ����
		XYChart.Series barYearPCIncome = new XYChart.Series();
		barYearPCIncome.setName("PC����"); // ��Ʈ�� ��
		ObservableList PCIncomeList = FXCollections.observableArrayList();
		
		PCIncomeList.add(new XYChart.Data(lbYear.getText(), Integer.parseInt(lbPCIncomeYear.getText())));
		barYearPCIncome.setData(PCIncomeList);
		barChart.getData().add(barYearPCIncome);
		
		// ��ǰ ����
		XYChart.Series barYearItemIncome = new XYChart.Series();
		barYearItemIncome.setName("��ǰ����"); // ��Ʈ�� ��
		ObservableList ItemIncomeList = FXCollections.observableArrayList();
		
		ItemIncomeList.add(new XYChart.Data(lbYear.getText(), Integer.parseInt(lbItemIncomeYear.getText())));
		barYearItemIncome.setData(ItemIncomeList);
		barChart.getData().add(barYearItemIncome);
		
		
		// �� ����
		XYChart.Series barYearTotalIncome = new XYChart.Series();
		barYearTotalIncome.setName("�� �� ����"); // ��Ʈ�� ��
		ObservableList totalIncomeList = FXCollections.observableArrayList();
		
		totalIncomeList.add(new XYChart.Data(lbYear.getText(), Integer.parseInt(lbTotalIncomeYear.getText())));
		barYearTotalIncome.setData(totalIncomeList);
		barChart.getData().add(barYearTotalIncome);
		
		
		lbRightArrowYear.setOnMouseClicked(event -> {
			year = Integer.parseInt(lbYear.getText());
			year++;
			lbYear.setText(String.valueOf(year));

			lbPCIncomeYear.setText(String.valueOf(idao.getYearPCIncomeInfo(String.valueOf(year))));
			lbItemIncomeYear.setText(String.valueOf(idao.getYearItemIncomeInfo(String.valueOf(year))));
			lbTotalIncomeYear.setText(String.valueOf(idao.getYearTotalIncomeInfo(String.valueOf(year))));
			
			PCIncomeList.removeAll(PCIncomeList);
			PCIncomeList.add(new XYChart.Data(lbYear.getText(), Integer.parseInt(lbPCIncomeYear.getText())));
			barYearPCIncome.setData(PCIncomeList);
			barChart.getData().add(barYearPCIncome);
			
			ItemIncomeList.removeAll(ItemIncomeList);
			ItemIncomeList.add(new XYChart.Data(lbYear.getText(), Integer.parseInt(lbItemIncomeYear.getText())));
			barYearItemIncome.setData(ItemIncomeList);
			barChart.getData().add(barYearItemIncome);
			
			totalIncomeList.removeAll(totalIncomeList);
			totalIncomeList.add(new XYChart.Data(lbYear.getText(), Integer.parseInt(lbTotalIncomeYear.getText())));
			barYearTotalIncome.setData(totalIncomeList);
			barChart.getData().add(barYearTotalIncome);
		});
		lbLeftArrowYear.setOnMouseClicked(event -> {
			year = Integer.parseInt(lbYear.getText());
			year--;
			lbYear.setText(String.valueOf(year));

			lbPCIncomeYear.setText(String.valueOf(idao.getYearPCIncomeInfo(String.valueOf(year))));
			lbItemIncomeYear.setText(String.valueOf(idao.getYearItemIncomeInfo(String.valueOf(year))));
			lbTotalIncomeYear.setText(String.valueOf(idao.getYearTotalIncomeInfo(String.valueOf(year))));
			
			PCIncomeList.removeAll(PCIncomeList);
			PCIncomeList.add(new XYChart.Data(lbYear.getText(), Integer.parseInt(lbPCIncomeYear.getText())));
			barYearPCIncome.setData(PCIncomeList);
			barChart.getData().add(barYearPCIncome);
			
			ItemIncomeList.removeAll(ItemIncomeList);
			ItemIncomeList.add(new XYChart.Data(lbYear.getText(), Integer.parseInt(lbItemIncomeYear.getText())));
			barYearItemIncome.setData(ItemIncomeList);
			barChart.getData().add(barYearItemIncome);
			
			totalIncomeList.removeAll(totalIncomeList);
			totalIncomeList.add(new XYChart.Data(lbYear.getText(), Integer.parseInt(lbTotalIncomeYear.getText())));
			barYearTotalIncome.setData(totalIncomeList);
			barChart.getData().add(barYearTotalIncome);
		});
    }
}
