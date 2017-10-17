String.prototype.startsWith = function (prefix) {
  return prefix && this.length >= prefix.length
    && this.substring(0, prefix.length) === prefix;
};

if (!window.JSON)
  JSON = {};

if (typeof JSON.retrocycle !== 'function') {
  JSON.retrocycle = (function () {
    'use strict';

    var t_obj = typeof {}, t_arr = Object.prototype.toString.apply([]), t_str = typeof "";
    var walk = function (path, _xpath, array) {
      if (path.startsWith('$')) // 基于xpath直接定位
        return path;
      else { // 相对回溯定位
        var x, j = path.split('..'), k = -j.length + (array ? 2 : 1), last = j
          .slice(-1)[0].replace('/', '.');
        x = k < 0 ? _xpath.slice(0, k) : _xpath.slice(0);
        if (last && !last.startsWith('.') && !last.startsWith('['))
          last = '.' + last;
        path = x.join('.') + last;
      }
      return path; // 最终得到绝对xpath地址
    };

    return function ($) {
      var xpath = ['$'];
      (function rez(value) {
        var i, item, name, path, _x;
        if (value && typeof value === t_obj) {
          if (Object.prototype.toString.apply(value) === t_arr) {
            for (i = 0; i < value.length; i += 1) {
              item = value[i];
              if (item && typeof item === t_obj) {
                xpath.push(xpath.pop() + '[' + i + ']'); // 下标引用要合并分级
                path = item.$ref;
                if (typeof path === t_str)
                  value[i] = eval(walk(path, xpath, true));
                else
                  rez(item);
                if (_x = xpath.pop())
                  xpath.push(_x.slice(0, _x.indexOf('['))); // 下标引用还原分级
              }
            }
          } else {
            for (name in value) {
              if (value.hasOwnProperty(name)
                && typeof value[name] === t_obj) {
                xpath.push(name);
                item = value[name];
                if (item) {
                  path = item.$ref;
                  if (typeof path === t_str)
                    value[name] = eval(walk(path, xpath));
                  else
                    rez(item);
                }
                xpath.pop();
              }
            }
          }
        }
      })($);
      return $;
    }
  })();
}

Ext.decode = function () {
  var isNative = function () {
    var useNative = null;
    return function () {
      if (useNative === null) {
        useNative = Ext.USE_NATIVE_JSON && window.JSON
          && JSON.toString() == '[object JSON]';
      }
      return useNative;
    };
  }();
  var dc, doDecode = function (json) {
    return json ? eval("(" + json + ")") : "";
  };

  return function (json) {
    if (!dc) {
      dc = isNative() ? JSON.parse : doDecode;
    }
    // return dc(json);
    return JSON.retrocycle(dc(json));
  }
}();

Ext.apply(Ext.util.JSON, {
  decode: Ext.decode
});
