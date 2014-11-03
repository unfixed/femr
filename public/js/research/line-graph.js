
/*
 *
 *  Line Graph
 *
 */

var lineGraphModule = (function(){

    var graph_data = [];

    var publicObject = {};
    publicObject.setGraphData = function(jsonData){

        graph_data = [];

        for( var i = 0; i < jsonData.length; i++ ){

            graph_data[i] = {
                name: i,
                value: jsonData[i]
            }
        }

    };

    publicObject.buildGraph = function(){

        // remove any previous graph
        d3.selectAll("svg > *").remove();

        var margin = {top: 20, right: 30, bottom: 50, left: 60};

        // keep 3/2 width/height ratio
        var aspectRatio = 5/3;
        var containerWidth = $(".main").width();
        var containerHeight = containerWidth / aspectRatio;

        // Calculate height/width taking margin into account
        var graphWidth = containerWidth - margin.right - margin.left;
        var graphHeight = containerHeight - margin.top - margin.bottom;


        var xScale = d3.scale.linear()
            .domain(d3.extent(graph_data, function(d) { return d.name; }))
            .range([0, graphWidth]);

        var yScale = d3.scale.linear()
            .domain(d3.extent(graph_data, function(d) { return d.value; }))
            .range([graphHeight, 0]);

        var xAxis = d3.svg.axis()
            .scale(xScale)
            .orient("bottom");

        var yAxis = d3.svg.axis()
            .scale(yScale)
            .orient("left");

        var line = d3.svg.line()
            .x(function(d) { return xScale(d.name); })
            .y(function(d) { return yScale(d.value); });

        var chart = d3.select(".chart")
            .attr("width", containerWidth)
            .attr("height", containerHeight)
            .append("g")
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

        chart.append("g")
            .attr("class", "x axis")
            .attr("transform", "translate(0," + graphHeight + ")")
            .call(xAxis)
            .append("text")
            .attr("class", "title")
            .attr("x", graphWidth / 2 )
            .attr("y",  0 + margin.bottom)
            .style("text-anchor", "middle")
            .attr("dy", "-5px")
            .text("Ages");

        chart.append("g")
            .attr("class", "y axis")
            .call(yAxis)
            .append("text")
            .attr("class", "title")
            .attr("transform", "rotate(-90)")
            .attr("y", 0 - margin.left)
            .attr("x", 0 - (graphHeight / 2))
            .attr("dy", "1.25em")
            .style("text-anchor", "middle")
            .text("Number of Patients");


        chart.append("path")
            .datum(graph_data)
            .attr("class", "line")
            .attr("d", line);
    };

    return publicObject;

})();

