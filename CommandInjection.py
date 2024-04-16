if 'domain' in params:
    command = 'host' if os.name != 'nt' else 'nslookup'
    domain = params.get('domain', '/')[0]
    output = subprocess.check_output(
        ' '.join([command, domain]),
        shell=True,
        stderr=subprocess.STDOUT,
        stdin=subprocess.PIPE
    )
    content = '<pre>{}</pre>'.format(output.decode())
