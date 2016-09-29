function filterPatrol(val) {
  var filter = $("#current_filter");
  var current_filter = filter.text() + val.toString();
  var sel = $("select#patrol");
  var opts = sel.find("option:not([disabled])");
  opts.filter(function (i,e) {return ! e.value.startsWith(current_filter) })
      .wrap("<hide></hide>")
      .hide()
      .attr("disabled","disabled");
  // sel.val(opts.filter(":not([disabled]):first").val());
  sel.val(opts.filter(":not([disabled]):first").val());
  filter.text(current_filter);

}

function clearFilter() {
  var current_filter = "";
  var sel = $("select#patrol");
  var opts = sel.find("option[disabled]");
  opts.unwrap()
  opts.show();
  opts.removeAttr("disabled");
  sel.val(opts.first().val())
  $("#current_filter").text(current_filter);
}

function backspaceFilter() {
  var filter = $("#current_filter");
  var current_filter = filter.text();
  current_filter = current_filter.substr(0,current_filter.length -1);
  var sel = $("select#patrol");
  var opts = sel.find("option");
  opts.filter("[disabled]").filter(function (i,e) {return e.value.startsWith(current_filter) })
      .show()
      .removeAttr("disabled")
      .unwrap("hide");
  sel.val(opts.filter(":not([disabled]):first").val());
  filter.text(current_filter)
}

