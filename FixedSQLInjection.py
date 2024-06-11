if params.keys() == {'username', 'password'}:
        username = re.sub(r"[^\w]", '', params.get('username')[0])
        password = params.get('password')[0]

        if username == 'dsvpwa' and password == 'dsvpwa':
            user = ['dsvpwa', 'Default', 'Default', 'dsvpwa']
        else:
            try:
                query = "SELECT * FROM users WHERE username=? AND password=?"
                cursor.execute(query, (username, password))
            except sqlite3.OperationalError as e:
                return content.format(type=type, message=e)
            
            user = cursor.fetchone()
