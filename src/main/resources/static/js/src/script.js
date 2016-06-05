/*var width = 960,
    height = 500;

var force = d3.layout.force()
    .charge(-200)
    .linkDistance(30)
    .size([width, height]);

var svg = d3.select("body").append("svg")
    .attr("width", width)
    .attr("height", height);

var tip = d3.tip()
    .attr('class', 'd3-tip')
    .offset([-10, 0])
    .html(function(d) {
      return "<strong>Node:</strong> <span style='color:red'>" + d.name + "</span>";
    });

var colorScale = function(colorsNo) {
    if (colorsNo <= 10)
      return d3.scale.category10();
    else if (colorsNo <= 20)
      return d3.scale.category20();
    else 
      // fix colors
      return d3.scale.linear().range(['#9467bd', '#fdd0a2']);
};

svg.call(tip);

d3.json("data.json", function(error, graph) {
  if (error) throw error;

  var color = colorScale(graph.colorsNo);

  force
      .nodes(graph.nodes)
      .links(graph.edges)
      .start();

  var link = svg.selectAll(".link")
      .data(graph.edges)
    .enter().append("line")
      .attr("class", "link")
      .style("stroke-width", function(d) { return Math.sqrt(d.value); });

  var node = svg.selectAll(".node")
      .data(graph.nodes)
    .enter().append("circle")
      .attr("class", "node")

      .call(force.drag);

  node.append("title")
      .text(function(d) { return d.name; });

  force.on("tick", function() {
    link.attr("x1", function(d) { return d.source.x; })
        .attr("y1", function(d) { return d.source.y; })
        .attr("x2", function(d) { return d.target.x; })
        .attr("y2", function(d) { return d.target.y; })
        .style("stroke", function(d) { return color(d.color); });

    node.attr("cx", function(d) { return d.x; })
        .attr("cy", function(d) { return d.y; })
        .on('mouseover', tip.show)
        .on('mouseout', tip.hide);
  });
});*/

(function() {
    "use strict";

    var btn = document.getElementById('upload-btn'),
        picBox = document.getElementById('picbox'),
        errBox = document.getElementById('errormsg');

    var uploader = new ss.SimpleUpload({
        button: btn,
        url: 'upload',
        name: 'file',
        multiple: true,
        multipart: true,
        maxUploads: 2,
        maxSize: 1024,
        queue: false,
        allowedExtensions: ['txt'],
        accept: '.txt',
        debug: true,
        hoverClass: 'btn-hover',
        focusClass: 'active',
        disabledClass: 'disabled',
        responseType: 'json',
        onSizeError: function () {
            errBox.innerHTML = 'Files may not exceed 1024K.';
        },
        onExtError: function () {
            errBox.innerHTML = 'Invalid file type. Please select a TXT file.';
        },
        onComplete: function (file, response) {
            if (!response) {
                errBox.innerHTML = 'Unable to upload file';
            } else {
                picBox.innerHTML = response;
            }
        }
    });
}());