google.charts.load('current', {'packages':['corechart']});

google.charts.setOnLoadCallback(graficoPrincipal);
google.charts.setOnLoadCallback(graficoPrincipalReceptores);

function graficoPrincipal() {
      $.ajax({
        url: "/api/dashboard/montototal",
        dataType: "json",
      }).done(function (jsonData) {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'fecha_emision_to_month');
        data.addColumn('number', 'montototal');
        

        jsonData.forEach(function (row) {
          data.addRow([
            row.fecha_emision_to_month,
            row.montototal
          ]);
        });

        var options = {
          chart: {
            title: 'Reporte de Facturas',
            chartArea:{left:0,top:0,width:"100%",height:"100%"},
            width: 600,
            height: 600,
            legend: { position: 'top'}
          }
        };

        var formatter = new google.visualization.NumberFormat({fractionDigits: 2} );
        formatter.format(data, 1);

        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options );
      }).fail(function (jq, text, err) {
        console.log(text + ' - ' + err);
      });
}
function graficoPrincipalReceptores() {
      $.ajax({
        url: "/api/dashboard/montototalreceptor",
        dataType: "json",
      }).done(function (jsonData) {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'dniReceptor');
        data.addColumn('number', 'montototal');
        

        jsonData.forEach(function (row) {
          
          data.addRow([
            row.dniReceptor,
            row.montototal
          ]);
        });

        var options = {
          chart: {
            title: 'Reporte de Facturas',
            chartArea:{left:0,top:0,width:"100%",height:"100%"},
            width: 600,
            height: 600,
            legend: { position: 'top'}
          }
        };

        var formatter = new google.visualization.NumberFormat({fractionDigits: 2} );
        formatter.format(data, 1);

        var chart = new google.visualization.BarChart(document.getElementById('chart_div1'));
        chart.draw(data, options );
      }).fail(function (jq, text, err) {
        console.log(text + ' - ' + err);
      });
    
}