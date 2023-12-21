use searchenggine;
select Title,Link, (length(lower(Text))-length(replace(lower(Text),'java','')))/length('java') as count from page order by count desc limit 30

 