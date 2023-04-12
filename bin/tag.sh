# 拉取分支上现有的tags

echo "begin fetch tags"
#git fetch --tags
echo "fetch tags complete"

tagList=`git tag -l`
echo "tag list:"
echo $tagList
#获取最新版本tag
latestTag=$(git describe --tags `git rev-list --tags --max-count=1`)

echo -e "latest tag: ${latestTag}"

# 读取pom文件版本
version=`awk '/<version>[^<]+<\/version>/{gsub(/<version>|<\/version>/,"",$1);print $1;exit;}' ../pom.xml`
echo "version: $version"
newTag=`git tag -l ${version}`

if [ -z $newTag ]
then
   # 标签不存在 创建新标签
   `git tag v${version}`
   echo "create tag complete"
   #推到分支上
   echo "pushing tag..."
   # 可能因网络原因导致push失败 如何判断
  `git push origin v${version}`
    echo "push tag complete"
else
   echo "tag has exists"
fi