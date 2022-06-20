# بسم الله الرحمن الرحيم 

import math
import sympy as sp
import numpy as np
import matplotlib.pyplot as plt

g = -9.8 #m/s^2

# 1-D Motion Functions

def oneDMotion(target, missing, variables):
    '''
        Calculate values for 1-D motion with constant acceleration.
        Inputs:
            - target = string for the target variable (ex: 'vf')
            - missing = string for the missing variable (ex: 't')
            - variables = list of variable values in format of [xf, xi, vf, vi, a, t]
        Outputs:
            - target value
            - missing value
    '''
    xf = variables[0]
    xi = variables[1]
    vf = variables[2]
    vi = variables[3]
    a = variables[4]
    t = variables[5]

    if missing == 'vf' and target == 'xf':
        xf = xi + vi*t + 1/2*a*t**2
        return xf
    elif missing == 'vf' and target == 'xi':
        xi = xf - vi*t - 1/2*a*t**2
        return xi
    elif missing == 'vf' and target == 'vi':
        vi = (xf - xi - 1/2*a*t**2)/t
        return vi
    elif missing == 'vf' and target == 'a':
        a = (xf - xi - vi*t)*2/(t**2)
        return a
    elif missing == 'vf' and target == 't':
        t = (-vi + math.sqrt(vi**2 - 2*a*(xf - xi)))/a
        if t < 0:
            t = (-vi - math.sqrt(vi**2 - 2*a*(xf - xi)))/a
            vf = vi + a*t
            results = {'t': str(t)+' s',
                        'vf': str(vf)+' m/s'}
            return results
        else:
            vf = vi + a*t
            return t,vf
    else:
        print('TBD')

print(oneDMotion('t', 'vf', [18, 0, 0, 0, g, 0]))