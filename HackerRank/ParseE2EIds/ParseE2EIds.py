from xml.dom import minidom
xmldoc = minidom.parse('taulia_ted_citi_rec_20160816153027_0772.xml')
e2eids = xmldoc.getElementsByTagName('EndToEndId')
for id in e2eids:
    print('\'' + id.firstChild.data + '\',')