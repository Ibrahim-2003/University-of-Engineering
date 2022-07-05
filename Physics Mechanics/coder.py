# case query_unit_type:
# switch (response_unit_type){
#   case response_unit_type:
#       break;
# }

import pyperclip as pc

def code(units):
    answer = ''
    for unit in units:
        new_units = units.copy()
        new_units.remove(unit)
        answer += 'case "' + unit + '":\n'
        answer += 'switch(response_unit_type){\n'
        for nunit in new_units:
            answer += 'case "' + nunit + '":\n'
            answer += 'response = ' + unit + 'To' + nunit + '(query);\n'
            answer += 'response_value.setText(String.valueOf(response));\n'
            answer += 'break;\n'
        answer += 'default:\n'
        answer += 'response_value.setText(Objects.requireNonNull(query_value.getText()).toString());\n'
        answer += 'break;\n'
        answer += '}\n'
        answer += 'break;\n'
        

    return answer

units = ['mm', 'cm', 'm', 'km', 'in', 'ft', 'yd', 'mi', 'a', 'ac', 'ha']


result = code(units)
pc.copy(result)
print(result)