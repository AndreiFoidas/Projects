


def validateSentence(s):
    '''
        Check if a sentence has a word and if every word has at least 3 letters
        bonus: tries to eliminate all useless spaces and makes a new valid sentence
        input:
            s - the sentence
        outup:
            news - the sentence but transformed so it is a real sentence (if possible)
        errors:
            ValueError - if there is a word with less than 3 characters
            ValueError - theres no words given
    '''
    news=''
    ct=0
    s=s.strip()
    ssplit=s.split(' ')
    for sen in ssplit:
        if len(sen)<3 and sen!='':
            raise ValueError('Each word should have more than 3 letters')
        if sen!='':
            ct+=1
            news+=sen+' '
    if ct==0:
        raise ValueError('There should be at least a word')
    news=news[:len(news)-1]
    return news