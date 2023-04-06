// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#000';

// Pie Chart Example

function loadPieChart(pieData){
  console.log(pieData);
  var ctx = document.getElementById("myPieChart");
  var myPieChart = new Chart(ctx, {
    type: 'doughnut',
    data: {
      labels: ["Cơm bình dân", "Bánh mì", "Đồ uống", "Hoa quả tươi", "Đồ ăn nhẹ", "Sinh tố"],
      datasets: [{
        data: pieData,
        backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc', '#FF3399', '#FFFF66', '#ff3333'],
        hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf', '#CC0066', '#FFFF00', '#CC0000'],
        hoverBorderColor: "rgba(234, 236, 244, 1)",
      }],
    },
    options: {
      maintainAspectRatio: false,
      tooltips: {
        backgroundColor: "rgb(255,255,255)",
        bodyFontColor: "#858796",
        borderColor: '#dddfeb',
        borderWidth: 1,
        xPadding: 15,
        yPadding: 15,
        displayColors: false,
        caretPadding: 10,
      },
      legend: {
        display: false
      },
      cutoutPercentage: 80,
    },
  });
}
